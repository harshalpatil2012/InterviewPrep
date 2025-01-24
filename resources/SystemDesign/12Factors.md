


checkmark report ot QE sign off storyThe 12 factors of modern application development are:


Codebase - Single version-controlled repository
Dependencies - Explicitly declared and isolated
Config - Stored in environment variables
Backing Services - Treated as attached resources
Build, Release, Run - Strictly separated stages
Processes - Stateless and share-nothing
Port Binding - Self-contained services
Concurrency - Scale via process model
Disposability - Fast startup and graceful shutdown
Dev/Prod Parity - Keep environments similar
Logs - Treat as event streams
Admin Processes - Run as one-off tasks


Codebase
Meaning: Single version-controlled repository that maps to multiple deployments
Example: One Git repo deployed to dev/staging/prod environments

Dependencies
Meaning: All dependencies must be explicitly declared and isolated
Example: Using package.json to list exact versions needed

Config
Meaning: Configuration should be strictly separated from code and stored in environment
Example: Database URLs, API keys stored as environment variables, not hardcoded

Backing Services
Meaning: External services (databases, queues) treated as attached resources
Example: Connecting to MongoDB using connection URL from config

Build, Release, Run
Meaning: Strict separation between building code, combining with config, and running
Example: Build Docker image → Inject env vars → Run container

Processes
Meaning: Applications run as stateless processes, sharing nothing
Example: No local file storage, session data in Redis instead of memory

Port Binding
Meaning: Services should be self-contained and expose themselves via port binding
Example: Web app listens on PORT environment variable

Concurrency
Meaning: Scale horizontally using process model
Example: Running multiple instances using PM2 or Kubernetes

Disposability
Meaning: Fast startup and graceful shutdown for robustness
Example: Proper handling of SIGTERM signals, connection cleanup

Dev/Prod Parity
Meaning: Keep environments as similar as possible
Example: Using Docker to ensure consistent environment

Logs
Meaning: Treat logs as event streams, don't manage log files
Example: Writing structured logs to stdout, using log aggregators

Admin Tasks
Meaning: Run admin/maintenance tasks as one-off processes
Example: Database migrations run as separate scripts

# These principles together ensure:
Cloud-native scalability
Environment consistency
Easy deployment
Reliable operations