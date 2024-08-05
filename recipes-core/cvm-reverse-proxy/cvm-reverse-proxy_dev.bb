inherit go-mod
LICENSE = "CLOSED"

GO_IMPORT = "github.com/konvera/cvm-reverse-proxy"
SRC_URI = "git://${GO_IMPORT};protocol=https;branch=main"
SRCREV = "${AUTOREV}"


#GO_INSTALL = "${GO_IMPORT}/cmd/beacon-chain"
GO_LINKSHARED = ""

# GO_EXTLDFLAGS:append = ",-z,stack-size=0x800000"

# reproducible builds
INHIBIT_PACKAGE_DEBUG_SPLIT = '1'
INHIBIT_PACKAGE_STRIP = '1'
GO_EXTRA_LDFLAGS:append = " -s -w -buildid="
#GOBUILDFLAGS:append = " -buildvcs=false"
#GOBUILDFLAGS:append = " -race"

RDEPENDS:${PN}-dev += "bash"

do_compile[network] = "1"

