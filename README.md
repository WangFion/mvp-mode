# Mvp-Mode
An mvp mode package library can help you quickly build an mvp project.

This project includes java version and kotlin version, For more information please see the website of [mvp](https://www.jianshu.com/p/f660b475d381) and [gradle](https://www.jianshu.com/p/1bf4d5dee595).

# Getting start

**First, add the following information to build.gradle under the project root directory.**
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

**Second, add the following information to build.gradle under the mudole directory.**
```
dependencies {
    implementation 'com.github.WangFion:mvp-mode:1.0.0'
}
```

**Third, Add implementation class.**
```
class KotlinActivity : IActivity<KotlinPresenter>() {
    ......
    mPresenter.XXX()
}
```
```
class KotlinPresenter : IPresenter<KotlinActivity>() {
    ......
    mView.XXX()
}
```
Then, you can call the KotlinPresenter function through **mPresenter**, and call the KotlinActivity function through **mView**.

**That's over, just so easy !**
