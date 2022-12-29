# Лабораторная работа №1. Управление файлами и каталогами

1. Создать каталог test в домашнем каталоге пользователя.
```
mkdir ~/test
```

2. Создать в нем файл list, содержащий список всех файлов и поддиректориев каталога /etc (включая
скрытые) в таком виде, что можно однозначно определить какая из записей является именем файла, а
какая — названием директории.
```
ls -aF /etc > ~/test/list
```

3. Вывести в конец этого файла два числа. Сначала количество поддиректориев в каталоге /etc, а
затем количество скрытых файлов в каталоге /etc.

```
ls -aF /etc | grep / | wc -l >> ~/test/list
ls -aF /etc | grep ^\. | wc -l >> ~/test/list
```

4. Создать в каталоге test каталог links.
```
mkdir ~/test/links
```

5. Создать в каталоге links жесткую ссылку на файл list с именем list_hlink.
```
ln ~/test/list ~/test/links/list_hlink
```

6. Создать в каталоге links символическую ссылку на файл list с именем list_slink.
```
ln -s ~/test/list ~/test/links/list_slink
```

7. Вывести на экран количество имен (жестких ссылок) файла list_hlink, количество имен (жестких
ссылок) файла list и количество имен (жестких ссылок) файла list_slink.
```
ls -l ~/test/links/list_hlink | awk '{print $2}'
ls -l ~/test/list | awk '{print $2}'
ls -l ~/test/links/list_slink | awk '{print $2}'
```

8. Дописать в конец файла list_hlink число строк в файле list.
```
wc -l ~/test/list >> ~/test/links/list_hlink
```

9. Сравнить содержимое файлов list_hlink и list_slink. Вывести на экран YES, если файлы
идентичны.
```
if cmp ~/test/links/list_hlink ~/test/links/list_slink > /dev/null
then echo "YES"
else echo "NO"
fi
```

10. Переименовать файл list в list1.
```
mv ~/test/list ~/test/list1
```

11. Сравнить содержимое файлов list_hlink и list_slink. Вывести на экран YES, если файлы
идентичны.
```
if cmp ~/test/links/list_hlink ~/test/links/list_slink > /dev/null
then echo "YES"
else echo "NO"
fi
```

12. Создать в домашнем каталоге пользователя жесткую ссылку на каталог links.
```
ln ~/test/links ~/links
```

13. Создать в домашнем каталоге файл list_conf, содержащий список файлов с расширением .conf, из
каталога /etc и всех его подкаталогов.
```
find /etc -name "*.conf" -type f> ~/list_conf
```

14. Создать в домашнем каталоге файл list_d, содержащий список всех подкаталогов каталога /etc,
расширение которых .d.
```
find /etc -name "*.d" -type d> ~/list_d
```

15. Создать файл list_conf_d, включив в него последовательно содержимое list_conf и list_d.
```
cat ~/list_conf >> ~/list_conf_d | cat ~/list_d >> ~/list_conf_d
```

16. Создать в каталоге test скрытый каталог sub.
```
mkdir ~/test/.sub
```

17. Скопировать в него файл list_conf_d.
```
cp ~/list_conf_d ~/test/.sub
```

18. Еще раз скопировать туда же этот же файл в режиме автоматического создания резервной копии
замещаемых файлов.
```
cp --backup ~/list_conf_d ~/test/.sub
```

19. Вывести на экран полный список файлов (включая все подкаталоги и их содержимое) каталога
test.
```
ls -R ~/test
```

20. Создать в домашнем каталоге файл man.txt, содержащий документацию на команду man.
```
man man > ~/man.txt
```

21. Разбить файл man.txt на несколько файлов, каждый из которых будет иметь размер не более 1
килобайта.
```
split -b 1K ~/man.txt
```
22. Создать в каталоге test каталог man.dir.
```
mkdir ~/test/man.dir
```

23. Переместить одной командой все файлы, полученные в пункте 21 в каталог man.dir.
```
mv x* ~/test/man.dir
```

24. Собрать файлы в каталоге man.dir обратно в файл с именем man.txt.
```
cat ~/test/man.dir/* > ~/test/man.dir/man.txt
```

25. Сравнить файлы man.txt в домашней каталоге и в каталоге man.dir и вывести YES, если файлы
идентичны.
```
if cmp ~/man.txt ~/test/man.dir/man.txt > /dev/null
then echo "YES"
else echo "NO"
fi
```

26. Добавить в файл man.txt, находящийся в домашнем каталоге несколько строчек с произвольными
символами в начало файла и несколько строчек в конце файла.
```
echo "~BEGIN~" > ~/tmp
cat ~/man.txt >> ~/tmp
echo "~END~" >> ~/tmp
mv ~/tmp ~/man.txt
```

27. Одной командой получить разницу между файлами в отдельный файл в стандартном формате для
наложения патчей.
```
diff -c ~/test/man.dir/man.txt ~/man.txt > ~/diff.patch
```

28. Переместить файл с разницей в каталог man.dir.
```
mv ~/diff.patch ~/test/man.dir
```

29. Наложить патч из файла с разницей на man.txt в каталоге man.dir.
```
patch ~/test/man.dir/man.txt ~/test/man.dir/diff.patch
```

30. Сравнить файлы man.txt в домашней каталоге и в каталоге man.dir и вывести YES, если файлы
идентичны.
```
if cmp ~/man.txt ~/test/man.dir/man.txt > /dev/null
then echo "YES"
else echo "NO"
fi
```

Предъявить скрипт преподавателю и ответить на его вопросы по любым использованным командам и
выполняющимся операциям. 

Перед сдачей лабораторной работы подумать, как объяснить результаты
выполнения пунктов 7, 9, 11, 25, 30