# RxMVVMReddit
Reddit application implementing Android MVVM + RxJava Architecture

Introduction
------------
### Android MVVM + RxJava Architecture
This repository contains Android Reddit application implementing [Model–View–ViewModel][13] architecture pattern and [RxJava][20].

[Model–View–ViewModel][13] pattern consists of : 

* The View - receives user's actions and notify the ViewModel, shows data from ViewModel

* The ViewModel - consumes streams of data from DataModel and exposes them to the View

* The DataModel - abstracts remote or data source and exposes them to ViewModel

Please refer to [https://en.wikipedia.org/wiki/Model-view-viewmodel](https://en.wikipedia.org/wiki/Model-view-viewmodel) for more detailed information about Model–View–ViewModel pattern.

[RxJava][20] extends the [Observer pattern][10] to support sequences of data/events so that it can help develop an Android application based on [Reactive Programming][23]. 

* The application implements a RESTFul API call with [Retrofit][1] and [RxJava][20]. 

* RxJava Call adapter of Retrofit makes Observable object used for receiving a response instead of normal Call object.

Please refer to [https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava) for more detailed information about RxJava.

Here is the architecture of this application.
<p><img src=https://softpian.github.io/gifs/RxMVVMReddit.png width="700" /></p>

Please refer to [https://github.com/googlesamples/android-architecture](https://github.com/googlesamples/android-architecture) in order to understand the overall Android Architecture including MVVM.

### Reddit RESTful API
This application uses RESTful API of [Reddit][0] which is a social news aggregation web site in order to get top 20 news posts.

It receives news data formatted in JSON and converts it to Java Objects including news title, photo url, author's name, date, comment's count and so on.
#### API Used

```
https://www.reddit.com/top.json?after=""&limit=20
```
Please visit [https://www.reddit.com/dev/api/](https://www.reddit.com/dev/api/) for more detailed information about API.

[0]: https://www.reddit.com/

### Android development skills
This repository is able to help understand how to use the following skills.
* How to implement Android Model-View-ViewModel architecture pattern with RxJava
* How to integrate Retrofit with RxJava through RxJava Call adapter of Retrofit
* How to call RESTful API with Retrofit
* How to use OkHttp Logging Intercepter in order to debug HTTP request/response data  
* How to convert JSON to Java Objects with Moshi
* How to load images from a remote server with Glide
* How to reduce boilerplate codes with ButterKnife

Libraries Used
---------------
* [RxJava][20] - Java VM implementation of Reactive Extensions
* [Retrofit][1] - Type-safe HTTP client for Android and Java which makes it easier to consume RESTful API services.
* [Retrofit 2 RxJava 2 Adapter][6] - Helps handle a response from server with RxJava as custom Call adapter
* [OkHttp Logging Intercepter][2] - Logs HTTP request and response data with different logging levels in order to debug HTTP error 
* [Moshi][3] - JSON library for Android and Java which makes it easy to parse JSON into Java objects. Used with Retrofit Moshi converter
* [Glide][4] - A fast and efficient image loading library for Android focused on smooth scrolling which offers an easy to use
* [ButterKnife][5] - Binds field and method for Android views with annotation processing and it reduces boilerplate codes


[1]: http://square.github.io/retrofit/
[2]: https://github.com/square/okhttp/wiki/Interceptors
[3]: https://github.com/square/moshi
[4]: https://bumptech.github.io/glide/
[5]: http://jakewharton.github.io/butterknife/
[6]: https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2

Reference
---------
* [Arndroid Architecture Blueprints][11]
* [TODO-MVVM-RXJAVA of Google][12]
* [Model–View–ViewModel pattern][13]
* [RxJava][20]
* [Reactive Extensions][21]
* [Reactive Programming][23]
* [Observer pattern][10]

[10]: https://en.wikipedia.org/wiki/Observer_pattern
[11]: https://github.com/googlesamples/android-architecture
[12]: https://github.com/googlesamples/android-architecture/tree/dev-todo-mvvm-rxjava/
[13]: https://en.wikipedia.org/wiki/Model-view-viewmodel
[20]: https://github.com/ReactiveX/RxJava
[21]: http://reactivex.io/
[23]: https://en.wikipedia.org/wiki/Reactive_programming

License
-------

    Copyright Jaemoon Hwang <jaemoon.hwang@gmail.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
