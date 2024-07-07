# tools

Personal android tools code.

[Install from JitPack](https://jitpack.io/#com.github.DeweyReed/tools)

## Changelog

### 0.8.0

- Android 14(34)
- AGP 8.3.2, Kotlin 1.9.24

### 0.7.0

- AGP 8.0.1 and Kotlin 1.8.21.
- Added `Lifecycle.doOnStart` and `Lifecycle.doOnResume`
- Added `Window.getInsetsControllerCompat`
- Added `PackageManagerCompat.getInstalledPackages`
- Moved `PackageManager` compat methods into `PackageManagerCompat`
- Removed `Bundle` and `Intent` compat methods in favor of `BundleCompat` and `IntentCompat`
- Removed `*.dip`, `View.dp` and `Fragment.dp`

### 0.6.0

- Added the support for Android 13
- Rename the package name from `xyz.aprildown` to `com.github.deweyreed`
- Added compatible methods for `Bundle`, `Intent` and `PackageManager`
- Removed `AudioFocusManager`

### 0.5.0

- Added `Lifecycle.doOnLifecycleEvent`, `Lifecycle.doOnDestroy`, `SavedStateHandle.value`
  and `ThemeColorUtils.setAlpha`
- `TextView.setTextIfChanged` and `EditText.setTextAndSelectEnd` accept nullables
- Removed `PendingIntentCompat`

## License

[LICENSE: Apache License 2.0](./LICENSE)
