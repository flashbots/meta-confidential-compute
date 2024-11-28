DESCRIPTION = "enables persistent disk encryption via tpm2"
LICENSE = "CLOSED"
RDEPENDS:${PN} += "cryptsetup tpm2-abrmd tpm2-tss tpm2-tools e2fsprogs-mke2fs util-linux-lsblk"
MACHINE_FEATURES += "disk-encryption"


python () {
    # Check if TARGET_LUN is set in the BitBake environment or in local.conf
    target_lun = d.getVar('TARGET_LUN')

    if target_lun is None:
        # If not set in BitBake environment, check the original environment
        origenv = d.getVar("BB_ORIGENV", False)
        if origenv:
            target_lun = origenv.getVar('TARGET_LUN')

    if target_lun:
        # If TARGET_LUN is set (to any non-empty value), keep its value
        d.setVar('TARGET_LUN', target_lun)
    else:
        # If TARGET_LUN is not set, set it to '10' by default
        d.setVar('TARGET_LUN', '10')

    bb.note("Disk LUN number is set to %s" % d.getVar('TARGET_LUN'))
}

FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI += "file://init"

INITSCRIPT_NAME = "disk-encryption"
INITSCRIPT_PARAMS = "defaults 88"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/disk-encryption

    # Create environment file for TARGET_LUN
    install -d ${D}${sysconfdir}/default
    echo "TARGET_LUN=${TARGET_LUN}" > ${D}${sysconfdir}/default/disk-encryption
}

FILES:${PN} += "${sysconfdir}/default/disk-encryption"
