rootProject.name = "netplix3"

include("netplix-adapters:adapter-http")
include("netplix-adapters:adapter-persistence")
include("netplix-adapters:adapter-redis")

include("netplix-apps:app-api")
include("netplix-apps:app-batch")

include("netplix-commons")

include("netplix-core:core-domain")
include("netplix-core:core-port")
include("netplix-core:core-service")
include("netplix-core:core-usecase")

