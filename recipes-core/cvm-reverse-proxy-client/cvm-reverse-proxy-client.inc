SUMMARY = "Client Side reverse proxy with confidentialVM attestation support"
HOMEPAGE = "https://github.com/flashbots/cvm-reverse-proxy"
LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://src/${GO_WORKDIR}/LICENSE;md5=4ae09d45eac4aa08d013b5f2e01c67f6"

inherit go-mod update-rc.d

INITSCRIPT_NAME = "cvm-reverse-proxy-client-init"
INITSCRIPT_PARAMS = "defaults 87"

GO_IMPORT = "github.com/flashbots/cvm-reverse-proxy"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main \
           file://cvm-reverse-proxy-client-init"

GO_INSTALL = "${GO_IMPORT}/cmd/proxy-client"
GO_LINKSHARED = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
GO_EXTRA_LDFLAGS:append = " -s -w -buildid= -X ${GO_IMPORT}/common.Version=${PV}"

do_compile[network] = "1"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES:${PN} = "${sysconfdir}/init.d/${INITSCRIPT_NAME} ${bindir}/proxy-client"
