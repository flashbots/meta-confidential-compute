require ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2', 'base-files-tpm2.inc', '', d)}

do_install:append() {
    # Add a global umask setting for file creation 640 and directory creation 750 permissions
    echo "umask 027" >> ${D}${sysconfdir}/profile
}
