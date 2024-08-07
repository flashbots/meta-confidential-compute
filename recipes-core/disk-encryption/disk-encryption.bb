DESCRIPTION = "enables persistent disk encryption of /dev/sda2 via tpm2"
LICENSE = "CLOSED"
RDEPENDS:${PN} += "cryptsetup tpm2-abrmd tpm2-tss tpm2-tools e2fsprogs-mke2fs"
MACHINE_FEATURES += "disk-encryption"
FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI += "file://init"

INITSCRIPT_NAME = "disk-encryption"
INITSCRIPT_PARAMS = "defaults 94"

inherit update-rc.d

do_install() {
	install -d ${D}${sysconfdir}/init.d
        cp ${WORKDIR}/init ${D}${sysconfdir}/init.d/disk-encryption
        chmod 755 ${D}${sysconfdir}/init.d/disk-encryption
}
