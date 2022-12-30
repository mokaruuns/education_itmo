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
all_users=$(cat /etc/passwd | cut -d: -f1)
for user in $all_users
do
    echo "user $user has id $(id -u $user)" >> work3.log
done
```

2. добавляет в файл work3.log строку, содержащую дату последней смены пароля для пользователя `root`;

```bash
#!/bin/bash
user=root
date=$(chage -l $user | head -1| cut -d: -f2)
echo "date of last password change for user $user is $date" >> work3.log
```

3. добавляет в файл work3.log список всех групп в системе (только названия групп) через запятую
    
```bash
#!/bin/bash
all_groups=$(cat /etc/group | cut -d: -f1)
echo "$(awk '{print $0}' ORS="," <<< "$all_groups" | sed 's/,$//')" >> work3.log
```

4. делает так, чтобы при создании нового пользователя у него в домашнем каталоге создавался файл readme.txt с текстом «Be careful!»

```bash
#!/bin/bash
echo "Be careful!" > /etc/skel/readme.txt
```

5. создает пользователя u1 с паролем 12345678

```bash
#!/bin/bash

useradd -m -p 12345678 u1
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

user=u1
echo "user $user has id $(id -u $user) and belongs to groups $(id -Gn $user)" >> work3.log
```

9. делает так, чтобы пользователь user дополнительно входил в группу g1

```bash
#!/bin/bash

user=user
usermod -a -G g1 $user
```

10. добавляет в файл work3.log строку с перечнем пользователей в группе g1 через запятую

```bash
#!/bin/bash

group=g1
echo "$(awk '{print $0}' ORS="," <<< "$(cat /etc/group | grep $group | cut -d: -f4)" | sed 's/,$//')" >> work3.log
```