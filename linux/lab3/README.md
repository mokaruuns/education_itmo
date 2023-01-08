# Лабораторная работа №3. Управление пользователями, группами пользователей и правами на файлы и каталоги в Linux

groupadd, useradd, groupdel, userdel, groupmod,
usermod, openssl, chage, chmod, chgrp, chown, groups, id.

## Команды

- `groupadd` - создание группы пользователей;
- `useradd` - создание пользователя;
- `groupdel` - удаление группы пользователей;
- `userdel` - удаление пользователя;
- `groupmod` - изменение группы пользователей;
- `usermod` - изменение пользователя;
- `openssl` - генерация сертификата;
- `chage` - изменение срока действия пароля;
- `chmod` - изменение прав доступа к файлу;
- `chgrp` - изменение группы владельца файла;
- `chown` - изменение владельца файла;
- `groups` - вывод списка групп, в которых состоит пользователь;
- `id` - вывод информации о пользователе.

## Создать скрипт, который будет выполнять следующие действия:
1. выводит в файл work3.log построчно список всех пользователей в системе в следующем формате: `user NNN has id MM`;

```bash
#!/bin/bash
echo "Problem 1" >> /home/work3.log
all_users=$(cat /etc/passwd | cut -d: -f1)
for user in $all_users
do
    echo "user $user has id $(id -u $user)" >> /home/work3.log
done
```

2. добавляет в файл work3.log строку, содержащую дату последней смены пароля для пользователя `root`;

```bash
#!/bin/bash
echo "Problem 2" >> /home/work3.log

user=root
date=$(chage -l $user | head -1| cut -d: -f2)
echo "date of last password change for user $user is $date" >> /home/work3.log
```

3. добавляет в файл work3.log список всех групп в системе (только названия групп) через запятую
    
```bash
#!/bin/bash
echo "Problem 3" >> /home/work3.log

all_groups=$(cat /etc/group | cut -d: -f1)
echo "$(awk '{print $0}' ORS="," <<< "$all_groups" | sed 's/,$//')" >> /home/work3.log
```

4. делает так, чтобы при создании нового пользователя у него в домашнем каталоге создавался файл readme.txt с текстом «Be careful!»

```bash
#!/bin/bash
echo "Be careful!" > /etc/skel/readme.txt
```

5. создает пользователя u1 с паролем 12345678

```bash
#!/bin/bash

useradd -m -p $(openssl passwd -1 12345678) u1
```

6. создает группу g1

```bash
#!/bin/bash

groupadd g1
```

7. делает так, чтобы пользователь u1 дополнительно входил в группу g1

```bash
#!/bin/bash

usermod -a -G g1 u1
```

8. добавляет в файл work3.log строку, содержащую сведения об идентификаторе и имени
пользователя u1 и идентификаторах и именах всех групп, в которые он входит

```bash
#!/bin/bash
echo "Problem 8" >> /home/work3.log

user=u1
echo "user $user has id $(id -u $user) and belongs to groups $(id -Gn $user)" >> /home/work3.log
```

9. делает так, чтобы пользователь user(mokaruuns) дополнительно входил в группу g1

```bash
#!/bin/bash

user=user
usermod -a -G g1 $user
```

10. добавляет в файл work3.log строку с перечнем пользователей в группе g1 через запятую

```bash
#!/bin/bash
echo "Problem 10" >> /home/work3.log


group=g1
echo "$(awk '{print $0}' ORS="," <<< "$(cat /etc/group | grep $group | cut -d: -f4)" | sed 's/,$//')" >> /home/work3.log
```

11.  делает так, что при входе пользователя u1 в систему вместо оболочки bash автоматически
запускается `/usr/bin/mc`, при выходе из которого пользователь возвращается к вводу логина и
пароля

```bash
#!/bin/bash

user=u1
echo "/usr/bin/mc" >> /home/$user/.bash_profile
```

12.  создает пользователя u2 с паролем 87654321

```bash
#!/bin/bash

useradd -m -p $(openssl passwd -1 87654321) u2
```


13. в каталоге /home создает каталог test13, в который копирует файл work3.log два раза с
разными именами (work3-1.log и work3-2.log)

```bash
#!/bin/bash

cp /home/work3.log /home/test13/work3-1.log
cp /home/work3.log /home/test13/work3-2.log
```

14. сделает так, чтобы пользователи u1 и u2 смогли бы просматривать каталог test13 и читать эти
файлы, только пользователь u1 смог бы изменять и удалять их, а все остальные пользователи
системы не могли просматривать содержимое каталога test13 и файлов в нем. При этом никто не
должен иметь права исполнять эти файлы

```bash
#!/bin/bash

groupadd group13
usermod -a -G group13 u1
usermod -a -G group13 u2
chown u1:group13 /home/test13 -R
chmod 640 /home/test13 -R
```

15. создает в каталоге /home каталог test14, в который любой пользователь системы сможет
записать данные, но удалить любой файл сможет только пользователь, который его создал или
пользователь u1

```bash
#!/bin/bash

mkdir /home/test14
chmod 755 /home/test14
chown u1 /home/test14 -R
```

16.  копирует в каталог test14 исполняемый файл редактора nano и делает так, чтобы любой
пользователь смог изменять с его помощью файлы, созданные в пункте 13

```bash
#!/bin/bash

cp /usr/bin/nano /home/test14
chmod 755 /home/test14/nano
```

17.  создает каталог test15 и создает в нем текстовый файл /test15/secret_file. Делает так, чтобы
содержимое этого файла можно было вывести на экран, используя полный путь, но чтобы узнать
имя этого файла, было бы невозможно

```bash
#!/bin/bash

mkdir /home/test15
echo "secret file"  > /home/test15/.secret_file
chmod 111 /home/test15
chmod 444 /home/test15/.secret_file
```



