DESCRIPTION = "enables swapfile on the persistent storage created by disk encryption"
LICENSE = "CLOSED"
MACHINE_FEATURES += "swapfile"
FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI += "file://init"

INITSCRIPT_NAME = "swapfile"
INITSCRIPT_PARAMS = "defaults 95"

inherit update-rc.d

# Default swapfile size (in GB)
SWAPFILE_SIZE ?= "512"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    cp ${WORKDIR}/init ${D}${sysconfdir}/init.d/swapfile
    chmod 755 ${D}${sysconfdir}/init.d/swapfile
    
    # Replace the swapfile size in the init script
    sed -i 's/SWAPFILE_SIZE=.*/SWAPFILE_SIZE=${SWAPFILE_SIZE}/' ${D}${sysconfdir}/init.d/swapfile
}

# Disabled for provisioning PoC
# RDEPENDS:${PN} += "disk-encryption"
