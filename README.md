MultiScrollView
===============

The MultiScrollView extends the ScrollView class to enable user to scroll in the child's layout instead of the parent view.



Adding
------
Gradle template ([check current version](http://search.maven.org/#search%7Cga%7C1%7Cmultiscrollview)):
```
dependencies {
    compile 'com.github.winsonlim:multiscrollview:0.1.+'
}
```

Maven template ([check current version](http://search.maven.org/#search%7Cga%7C1%7Cmultiscrollview)):
```
<dependency>
    <groupId>com.github.winsonlim</groupId>
    <artifactId>multiscrollview</artifactId>
    <version>0.1.5</version>
</dependency>
```




To-Use
------
MultiScrollView childScrollView = (MultiScrollView) convertView.findViewById(R.id.child_scrollview);
childScrollView.init(convertView.findViewById(R.id.child_layout));
