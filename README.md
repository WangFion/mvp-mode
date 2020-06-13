# Mvp-Mode
An mvp mode package library can help you quickly build an mvp project.

This project includes java version and kotlin version, For more information please see the website of [mvp](https://www.jianshu.com/p/f660b475d381) and [gradle](https://www.jianshu.com/p/1bf4d5dee595).

# Getting start

**First, add the following information to build.gradle under the project root directory.**
```
allprojects {
	repositories {
		......
		maven { url 'https://jitpack.io' }
	}
}
```

**Second, add the following information to build.gradle under the module directory.**
```
dependencies {
    implementation 'com.github.WangFion:mvp-mode:latest'
}
```
The latest release version is [![](https://jitpack.io/v/WangFion/mvp-mode.svg)](https://jitpack.io/#WangFion/mvp-mode)

**Third, Add implementation class.**
```java
// java code
public class JavaActivity extends IActivity<JavaIPresenter> {
    ......
    @Override
    protected void initData() {
        mPresenter.XXX();
    }
    ......
}

public class JavaIPresenter extends IPresenter<JavaActivity> {
    ......
    @Override
    protected void init() {
        mView.XXX();
    }
    ......
}
```
```kotlin
// kotlin code
class KotlinActivity : IActivity<KotlinPresenter>() {
    ......
    override fun initData() {
        mPresenter?.XXX()
    }
    ......
}

class KotlinPresenter : IPresenter<KotlinActivity>() {
    ......
    override fun init() {
        mView?.XXX()
    }
    ......
}:
```
Then, you can call the KotlinPresenter function through **mPresenter**, and call the KotlinActivity function through **mView**.

**That's over, just so easy !**
