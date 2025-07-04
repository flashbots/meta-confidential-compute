FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://startup.nsh"

do_deploy:append() {
    install -m 0644 ${WORKDIR}/startup.nsh ${DEPLOYDIR}
}
