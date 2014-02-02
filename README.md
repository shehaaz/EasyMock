**Learning EasyMock using Vogella's tutorial:**
http://www.vogella.com/tutorials/EasyMock/article.html

* EasyMock instantiates an object based on an interface or class.

	* ```createNiceMock() ``` : creates a mock which returns default values for methods which are not overridden.

	* Mock(): generic

	* expect(): tells EasyMock to simulate a method with certain arguments. 

	* andReturn(): defines the return value of this method for the specified method parameters. 

	* times(): defines how often the Mock object will be called. 

	* replay(): is called to make Mock object available. 