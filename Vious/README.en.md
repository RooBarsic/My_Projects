<h2> Vious - a virtual assistant for learning English </h2>

Vious is an application designed to help people learn English.

<h3> Short description of the features. </h3>

You can add those words to Vious - that you want to study, the program will store them in its memory (locally) and use it for further work.

There is a learning mode - to study the added words, and to repeat the previously learned ones. A special algorithm has been added to the learning mode for more efficient learning.

The main feature of Vious is that it simplifies and facilitates the study of English through video and audio materials with subtitles. For example on movies, TV shows, cartoons, etc. . (more in the extended description)

<h3> News. </h3>

.......... Vious-2.0: The first version of Vious in console mode.

01/26/2019 Vious-3.0: added the possibility of online translation using https://translate.yandex.ru/

<h3> Vious has various commands for working: </h3>
1) #exit - the command is intended to exit the program
2) #stats - displays information about the total number of words, number of words learned, number of words not studied
3) #parse - takes a file with subtitles, scans a file - writing individual words into the resulting file.
4) #compress - compresses a file with words - deleting similar words, and saves only unique words to the resulting file.
5) #new_from_file - takes a file with new words (format "first - number of words, then all words in English. At the end - translation of words into Russian") and adds those words - which Vious does not have in the database. Example file https://github.com/RooBarsic/My_Projects/blob/master/Vious/Vious-2.0/new_words.txt
6) #new - to add new words. First enter the number of words to be added, then the words themselves with the translation.
7) #Alisa_learn - adding new words, while you do not translate words. Words are translated using https://translate.yandex.ru/ (Translated by Yandex.Translate service http://translate.yandex.ru/.), And you confirm whether you want to add such a translation. This command has been added to Vious-3.0.
8) #learn - switch to learning mode. In this mode, there are two groups of words - studied and not studied.
There are three commands in the training mode:

8.1) 1 - I know the translation of the word (if you remember the translation well, then this word will go into the group of people studied)

8.2) 2 - I do not know the translation of the word (if you don’t remember the translation, then this word will go into the group not studied)

8.3) 3 - complete the training with this group of words

<h3> Author Comments: </h3>
The application is under development.

01/26/2019 At the moment, the application is running in console mode with support for the above commands to work in the latest version.

Expected version with a graphical interface and improved learning algorithms.

<h3> Usage Tips: </h3>
1) Use frequent and short workouts, for example, 15-20 minutes in the morning and at night (as well as in other free time).
2) Add movie subtitles - that you want to watch.

<h3> Technologies: </h3>
The program is written in the Java programming language. Uses yandex.translate API, and work with files.
Translated by Yandex.Translate service http://translate.yandex.ru/.
