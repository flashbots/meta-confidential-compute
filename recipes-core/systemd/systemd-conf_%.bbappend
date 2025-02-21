FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://journald.conf"

do_install:append() {
    install -d ${D}${sysconfdir}/systemd/journald.conf.d
    install -m 0644 ${WORKDIR}/journald.conf ${D}${sysconfdir}/systemd/journald.conf.d/99-custom.conf
}

FILES:${PN} += "${sysconfdir}/systemd/journald.conf.d/99-custom.conf"
