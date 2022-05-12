# Build Tracker Plugin

## What is Build Tracker?

A tiny Gradle Plugin which helps to extract and trace _build_ insights on each _Android_ build.

## Features
- Traces every build process of the given _Android_ project and collects valuable insights.
- Support for Multi-Module Android projects (including [Dynamic-Feature modules](https://developer.android.com/guide/playcore/feature-delivery)) as well.
- Generates an output file containing collected insights from every particular artifact assembler build process.
- Provides an option to store those insights in a [Cloud Firestore Database](https://firebase.google.com/docs/firestore) via the [client](https://github.com/alperbry/build-tracker-client) embedded inside of it.

A sample Android project build output:
```bash
{
   "projectId":"my-beautiful-sample-project",
   "stateIdentifier":"<vcs-revision-hash>",
   "timestamp":"2022-05-12T18:24:27.568648Z",
   "durationInMs":38318,
   "hardware":{
      "coreCount":6,
      "cpuModel":"Intel(R) Core(TM) i7-9750H CPU @ 2.60GHz",
      "environmentIdentifier":"MacBookPro16,1",
      "physicalMemoryInMb":16384
   },
   "operatingSystem":{
      "name":"MAC_OS",
      "architecture":"x86_64",
      "version":"10.15.7"
   },
   "outputs":[
      {
         "id":"io.github.alperbry.mybeautifulsampleproject",
         "moduleName":"app",
         "versionCode":"2",
         "flavorName":"dev",
         "buildType":"devDebug"
      },
      {
         "id":"io.github.alperbry.mybeautifulsampleproject",
         "moduleName":"dashboard",
         "versionCode":"2",
         "flavorName":"dev",
         "buildType":"devDebug"
      }
   ]
}
```

## How does Build Tracker handle Gradle Builds?

The main objective of this tool is to extract insights of Gradle builds in a _comparable_ manner with the help of _quantitative values_ and let developers make changes on both their development environments and on the projects accordingly. Taking that objective into consideration; Build Tracker traces the _builds_ which produces an artifact, rest is ignored.

A Gradle project can produce 1 to many artifact with a single command or even multiple commands at the same time. Build Tracker Plugin also keeps this feature in mind. Plugin accepts that any _build_ which assembles output from any module(s) of a root project, belongs to that particular project. It lists artifact related details in the outputs list.

## Setup

-  Add this to your <b>project root</b> build.gradle file.
```bash
plugins {
    id 'io.github.alperbry.build-tracker' version '0.1alpha1'
}
```
- If you have a _ready_ Firebase project, configure Build Tracker Plugin with your Firebase app credentials to make use of it. Add this to <b>project root</b> build.gradle file.
```bash
buildTracker {
    firebase {
        email <user-email-for-your-firebase-app>
        password <user-email-for-your-firebase-app>
        apiKey <api-key-of-your-firebase-app>
        databaseId <id-of-your-firestore-db>
    }
}
```
It is strongly suggested <b>not to</b> hardcode those values in <b>any publicly accessible</b> file.

- Build Tracker Plugin stores the output file in the project level _build_ directory by default. If you want to override the directory;
```bash
buildTracker {
    outputDir <desired-directory-for-output-file>
}
```

- Build Tracker Plugin runs on every artifact assembling build by default. If you want to _conditionalize_ this behavior;
```bash
buildTracker {
    trackingEnabled <boolean-value-to-enable-tracking>
}
```

- Build Tracker Plugin uses root project name as project Id by default. If you want to override this naming;
```bash
buildTracker {
    projectId <your-custom-project-id>
}
```
