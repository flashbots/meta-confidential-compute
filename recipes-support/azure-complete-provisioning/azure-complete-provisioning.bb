SUMMARY = "Complete Azure VM provisioning"
DESCRIPTION = "Complete the provisioning of the Azure VM and report it is ready in the absence of the Azure VM Agent"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI += "file://azure-complete-provisioning"

INITSCRIPT_NAME = "azure-complete-provisioning"
INITSCRIPT_PARAMS = "defaults 61"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES:${PN} = "${sysconfdir}/init.d/${INITSCRIPT_NAME}"
