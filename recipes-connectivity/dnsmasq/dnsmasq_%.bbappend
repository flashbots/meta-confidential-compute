FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://dnsmasq.init"

inherit update-rc.d

INITSCRIPT_NAME = "dnsmasq"
INITSCRIPT_PARAMS = "defaults 15"

INSANE_SKIP:${PN} += "empty-dirs"

do_install:append() {
    # Install custom init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/dnsmasq.init ${D}${sysconfdir}/init.d/dnsmasq

    # Ensure log directory exists
    install -d ${D}${localstatedir}/log
}
