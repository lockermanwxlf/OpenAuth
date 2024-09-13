*For now, work will be pulled into the [dev branch](https://github.com/lockermanwxlf/OpenAuth/tree/dev) until pipelines are made to build (and possibly publish) upon pull to main*

OpenAuth is a TOTP client built in [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/).
It is compliant with the [Google Authenticator Key URI spec](https://github.com/google/google-authenticator/wiki/Key-Uri-Format).

Though Compose Multiplatform allows the application to be built for ios, the app is not built or published for ios as I do not have a mac. 
I do not have access to any ios-specific auto-completes, so making platform-specific code for ios is difficult, and I have no way of testing my code for ios.

# Planned features
* Store keys encrypted in cloud (such as in Google Drive).
Clients check for new keys on startup allowing keys to sync between devices.
