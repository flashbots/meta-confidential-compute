SUMMARY = "System API Service"
HOMEPAGE = "https://github.com/flashbots/system-api"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/${GO_WORKDIR}/LICENSE;md5=c7bc88e866836b5160340e6c3b1aaa10"

inherit go-mod update-rc.d

INITSCRIPT_NAME = "system-api-init"
INITSCRIPT_PARAMS = "defaults 81"

GO_IMPORT = "github.com/flashbots/system-api"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main \
           file://system-api-init"
SRCREV = "v0.5.0"

GO_INSTALL = "${GO_IMPORT}/cmd/system-api"
GO_LINKSHARED = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
GO_EXTRA_LDFLAGS:append = " -s -w -buildid= -X ${GO_IMPORT}/common.Version=${PV}"

do_compile[network] = "1"

do_compile() {
    cd ${S}/src/${GO_IMPORT}
    ${GO} build -trimpath -ldflags "${GO_EXTRA_LDFLAGS}" -o ${B}/system-api cmd/system-api/*.go
}

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/system-api ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}

FILES:${PN} = "${sysconfdir}/init.d/${INITSCRIPT_NAME} ${bindir}/system-api"
