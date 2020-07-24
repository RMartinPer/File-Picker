![](https://github.com/Ru95Gasol/File-Picker/blob/master/app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png)

# File Picker ![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat) [![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://opensource.org/licenses/Apache-2.0) [![](https://jitpack.io/v/Ru95Gasol/File-Picker.svg)](https://jitpack.io/#Ru95Gasol/File-Picker)
Android Library to select Files/Directories from Device Storage.

## Features

* Easy to Implement.
* No permissions required.
* File, Directory Selection.
* Single or Multiple File/Directory Selection.
* Internationalization: Arabic, Bengali, Catalan, Chinese, Czech, Dutch, English, French, German, Hindi, Hungarian, Indonesian, Italian, Japanese, Korean, Polish, Portuguese, Romanian, Russian, Spanish, Swedish, Turkish, Ukrainian and Urdu.

## Installation

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Ru95Gasol:File-Picker:1.0'
	}

## Usage

**If you are targeting Android 10 or higher. Set this to your manifest**
```
<manifest ... >
  <!-- This attribute is "false" by default on apps targeting
       Android 10 or higher. -->
  <application 
       android:requestLegacyExternalStorage="true" ........ >
    ......
  </application>
</manifest>
```

## FilePickerDialog
1. Start by creating an instance of `DialogProperties`.

    ```java
        DialogProperties properties = new DialogProperties();
        DialogProperties properties = new DialogProperties(isExternal: false or true);
    ```

    Note: If you pass false to the second constructor is like calling the first constructor and loads the default directory, if you pass true loads the external storage directory.

    Now 'DialogProperties' has certain parameters.

2. Assign values to each Dialog Property using `DialogConfig` class.

    ```java
        properties.setSelectionMode(DialogConfigs.SINGLE_MODE or DialogConfigs.MULTI_MODE);
        properties.setSelectionType(DialogConfigs.FILE_SELECT, DialogConfigs.DIR_SELECT or DialogConfigs.FILE_AND_DIR_SELECT);
        properties.setRoot(new File(DialogConfigs.DEFAULT_DIR or DialogConfigs.EXTERNAL_DIR));
        properties.setErrorDir(new File(DialogConfigs.DEFAULT_DIR or DialogConfigs.EXTERNAL_DIR));
        properties.setOffset(new File(DialogConfigs.DEFAULT_DIR or DialogConfigs.EXTERNAL_DIR));
        properties.setExtensions(null or new String[]{".ext1", ".ext2"});
        properties.setHiddenFilesShown(false or true);
    ```
    
    Note: For the root, error directory, and offset, be sure to pass them valid directory/file paths.

3. Next create an instance of `FilePickerDialog`, and pass `Context` and `DialogProperties` references as parameters. Optional: You can change the title of dialog. Default is current directory name. Set the positive button string. Default is Select. Set the negative button string. Defalut is Cancel.

    ```java
        FilePickerDialog dialog = new FilePickerDialog(MainActivity.this, properties);
        dialog.setTitle("Select a File");
    ```

4.  Next, Attach `DialogSelectionListener` to `FilePickerDialog` as below,
    ```java
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                //files is the array of the paths of files selected by the Application User.
            }
        });
    ```
    An array of paths is returned whenever user press the `select` button`.

5. Use ```dialog.show()``` method to show dialog.

## NOTE:
Marshmallow and above requests for the permission on runtime. You should override `onRequestPermissionsResult` in Activity/AppCompatActivity class and show the dialog only if permissions have been granted.

```java
        //Add this method to show Dialog when the required permission has been granted to the app.
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
            switch (requestCode) {
                case FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if(dialog!=null) {
			//Show dialog if the read permission has been granted.
                            dialog.show();
                        }
                    } else {
                        //Permission has not been granted. Notify the user.
                        Toast.makeText(MainActivity.this,"Permission is Required for getting list of files",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
```

    That's It. You are good to proceed further.

## Screenshot

### Select file

#### Initial dialog at the root

![Screenshot 1](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_file_1.png)

#### Dialog after opening a folder

![Screenshot 2](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_file_2.png)

#### Dialog after selecting a file in single mode

![Screenshot 3](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_file_3.png)

#### Dialog after selecting some files in multi mode

![Screenshot 4](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_file_4.png)

### Select folder

#### Initial dialog at the root

![Screenshot 5](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_folder_1.png)

#### Dialog after opening a folder

![Screenshot 6](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_folder_2.png)

#### Dialog after selecting a folder in single mode

![Screenshot 7](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_folder_3.png)

#### Dialog after selecting some folders in multi mode

![Screenshot 8](https://github.com/Ru95Gasol/File-Picker/blob/master/screenshots/select_folder_4.png)

## License
Copyright (C) 2020 File-Picker

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
