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
tr '\n' ',' < all_groups >> work3.log

```
