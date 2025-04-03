FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://logrotate-frequent.cron \
            file://default-logrotate.conf"

do_install:append() {
    # Remove the default daily cron job for logrotate
    rm -f ${D}${sysconfdir}/cron.daily/logrotate

    # Install a cron job that runs logrotate every 10 minutes
    install -d ${D}${sysconfdir}/cron.d
    install -m 0640 ${WORKDIR}/logrotate-frequent.cron ${D}${sysconfdir}/cron.d/logrotate-frequent

    # Install a default logrotate configuration
    install -d ${D}${sysconfdir}/logrotate.d
    install -m 0644 ${WORKDIR}/default-logrotate.conf ${D}${sysconfdir}/logrotate.d/default-logrotate

    # Create the logrotate state directory
    install -d ${D}${localstatedir}/lib/logrotate
}

FILES:${PN} += "${localstatedir}/lib/logrotate"
FILES:${PN} += "${sysconfdir}/cron.d/logrotate-frequent"
FILES:${PN} += "${sysconfdir}/logrotate.d/default-logrotate"
