require ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2', 'base-files-tpm2.inc', '', d)}

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://resolv.conf"

do_install:append() {
    # Add a global umask setting for file creation 640 and directory creation 750 permissions
    echo "umask 027" >> ${D}${sysconfdir}/profile

    # Install custom resolv.conf with localhost nameserver
    install -m 0644 ${WORKDIR}/resolv.conf ${D}${sysconfdir}/resolv.conf
}
