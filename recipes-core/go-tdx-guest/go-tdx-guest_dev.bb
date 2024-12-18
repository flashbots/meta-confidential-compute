SUMMARY = "Google TDX go guest tools"
HOMEPAGE = "https://github.com/google/go-tdx-guest"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_WORKDIR}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

inherit go-mod

GO_IMPORT = "github.com/google/go-tdx-guest"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main"
SRCREV = "${AUTOREV}"

GO_INSTALL = "${GO_IMPORT}/tools/attest ${GO_IMPORT}/tools/check ${GO_IMPORT}/tools/extend"
GO_LINKSHARED = ""

INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
GO_EXTRA_LDFLAGS:append = " -s -w -buildid= -X ${GO_IMPORT}/common.Version=${PV}"

do_compile[network] = "1"

FILES:${PN} = "${bindir}/attest ${bindir}/check ${bindir}/extend"
