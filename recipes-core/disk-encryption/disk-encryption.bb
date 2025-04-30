DESCRIPTION = "enables persistent disk encryption via tpm2"
LICENSE = "CLOSED"
RDEPENDS:${PN} += "cryptsetup tpm2-abrmd tpm2-tss tpm2-tools e2fsprogs-mke2fs util-linux-lsblk"
MACHINE_FEATURES += "disk-encryption"

python() {
}

FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI += "file://init \
            file://disk-encryption-key.mustache"

INITSCRIPT_NAME = "disk-encryption"
INITSCRIPT_PARAMS = "defaults 88"

inherit update-rc.d

do_install() {
}
