DESCRIPTION = "enables persistent disk encryption via tpm2"
LICENSE = "CLOSED"
RDEPENDS:${PN} += "cryptsetup tpm2-abrmd tpm2-tss tpm2-tools e2fsprogs-mke2fs util-linux-lsblk"
MACHINE_FEATURES += "disk-encryption"

python() {
    def get_var_with_origenv(d, var_name):
        """Helper function to get variable from current or original environment."""
        value = d.getVar(var_name)
        if value is None:
            origenv = d.getVar("BB_ORIGENV", False)
            if origenv:
                value = origenv.getVar(var_name)
        return value

    # Set and validate TARGET_LUN
    target_lun = get_var_with_origenv(d, 'TARGET_LUN')
    d.setVar('TARGET_LUN', target_lun if target_lun else '10')
    bb.note(f"Disk LUN number is set to {d.getVar('TARGET_LUN')}")

    # Set and validate DISK_ENCRYPTION_KEY_STORAGE
    VALID_STORAGE_TYPES = {'tpm2', 'file'}
    storage = get_var_with_origenv(d, 'DISK_ENCRYPTION_KEY_STORAGE')
    storage = storage if storage else 'tpm2'

    if storage not in VALID_STORAGE_TYPES:
        bb.fatal(f"DISK_ENCRYPTION_KEY_STORAGE must be set to one of: {', '.join(VALID_STORAGE_TYPES)}")

    d.setVar('DISK_ENCRYPTION_KEY_STORAGE', storage)
    bb.note(f"Disk encryption key storage is set to '{d.getVar('DISK_ENCRYPTION_KEY_STORAGE')}'")
}

FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI += "file://init \
            file://disk-encryption-key.mustache"

INITSCRIPT_NAME = "disk-encryption"
INITSCRIPT_PARAMS = "defaults 88"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/disk-encryption

    # Create environment file for init service
    install -d ${D}${sysconfdir}/default ${D}${sysconfdir}/disk-encryption
    echo "TARGET_LUN=${TARGET_LUN}
DISK_ENCRYPTION_KEY_STORAGE=${DISK_ENCRYPTION_KEY_STORAGE}" > ${D}${sysconfdir}/default/disk-encryption
    if [ "${DISK_ENCRYPTION_KEY_STORAGE}" = "file" ]; then
        echo "DISK_ENCRYPTION_KEY_FILE=/etc/disk-encryption/key" >> ${D}${sysconfdir}/default/disk-encryption
        install -m 0644 ${WORKDIR}/disk-encryption-key.mustache ${D}${sysconfdir}/disk-encryption/key.mustache
    fi
}

FILES:${PN} += "${sysconfdir}/default/disk-encryption"
FILES:${PN}:append = " ${@bb.utils.contains('DISK_ENCRYPTION_KEY_STORAGE', 'file', '${sysconfdir}/disk-encryption/key.mustache', '', d)}"
