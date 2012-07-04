AS3Generator
============

This project generates the as3 (Flex) files for the java files.

Annotations
-----------
### as3.class - A class level tag.

Attributes:
	* name - The name of the generated AS class. Should be same as the java class name. Required.
	* generate - On false, does not generate the AS file for the java class. Default: true.
	* generate-bindable-metadata - Generates [Bindable] at class level in AS. Default value: true.
	* generate-remote-class-metadata - Generates [RemoteClass(alias="..")] on true. Default value: false.

### as3.field - A Field level tag.

Attributes:

	* type - The fully qualified flex type to be generated in the AS file. Required.
	* import - Imports the flex type defined above. Default: false.
	* generate-bindable-field-metadata - Generate field level [Bindable] in the AS file. Default: false.
	* generate - Controls generation of the field in as files. Default: true.

Java-Flex mapping
-----------------

1. int - Number
2. double - Number
3. long - Number
4. java.lang.Short - Number
5. java.lang.Byte - Number
6. java.lang.Integer - Number
7. java.lang.Double - Number
8. java.lang.Long - Number
9. java.lang.Float - Number
10. java.lang.String - String
11. java.lang.Character - String
12. java.util.Collection - mx.collection.ArrayCollection
13. java.util.Map - Object
14. java.util.Dictionary - Object

For any other type define the as3.field with the flex type, and if the import is necessary. If that does not solve the problem, define it as Object, then cast it in the flex code when necessary. If this too does not solve the problem contact me on my blog. Will add the feature and release the next version. 

Usage
-----
Write a ant build script and invoke xdoclet with target:

	<target name="generate">
	        <xdoclet>
	        <fileset dir="${basedir}/src">
	             <include name="**/*.java"/>          
	         </fileset>
	        <component classname="com.ssb.plugin.as3.As3Plugin"
	                   destdir="${basedir}/src"/>
	        </xdoclet>
	</target>

The sample folder has example of using all these features.

Dist
----

Download the jar from dist folder and put it in classpath.

API Doc
-------
api folder has the documentation.

More Documentation
------------------
	* http://techdiary.bitourea.com/2008/10/as-model-generator-xdoclet2-plugin-to.html or http://sacrosanctblood.blogspot.com/2008/10/as-model-generator-xdoclet2-plugin-to.html 
	* http://techdiary.bitourea.com/2008/10/xdoclet2-custom-plugin-actionscript3.html or http://sacrosanctblood.blogspot.com/2008/10/xdoclet2-custom-plugin-actionscript3.html
		
Licensing
---------
Released under MIT license, go ahead and use, modify, distribute as you wish. The license is provided in LICENSE.txt. The license of other libraries used must be used as defined by them. 		