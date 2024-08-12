inherit go-mod
LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://src/${GO_WORKDIR}/LICENSE;md5=4ae09d45eac4aa08d013b5f2e01c67f6"

GO_IMPORT = "github.com/konvera/cvm-reverse-proxy"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main"
SRCREV = "${AUTOREV}"


GO_LINKSHARED = ""

# reproducible builds
INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
GO_EXTRA_LDFLAGS:append = " -s -w -buildid="

RDEPENDS:${PN}-dev += "bash"

do_compile[network] = "1"

