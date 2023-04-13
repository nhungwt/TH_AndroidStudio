# TH_AndroidStudio

## Gradle
  plugins {
      id 'com.android.application'
  }

  android {
      compileSdk 32

      defaultConfig {
          applicationId "com.example.th_4_13"
          minSdk 29
          targetSdk 32
          versionCode 1
          versionName "1.0"

          testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
      }

      buildTypes {
          release {
              minifyEnabled false
              proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
          }
      }
      compileOptions {
          sourceCompatibility JavaVersion.VERSION_1_8
          targetCompatibility JavaVersion.VERSION_1_8
      }
  }

  dependencies {

      implementation 'androidx.appcompat:appcompat:1.4.0'
      implementation 'com.google.android.material:material:1.6.1'
      implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
      implementation 'androidx.legacy:legacy-support-v4:1.0.0'
      testImplementation 'junit:junit:4.+'
      androidTestImplementation 'androidx.test.ext:junit:1.1.5'
      androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
  }
  ## 
