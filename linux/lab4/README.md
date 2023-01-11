# Установка программного обеспечения

1. Установите из сетевого репозитория пакеты, входящие в группу «Developments Tools».
    
    ```bash
    yum install dnf
    yum groupinstall "Development Tools"
    ```


2. Установите из исходных кодов, приложенных к методическим указаниям пакет bastet-0.43. Для этого
необходимо создать отдельный каталог и скопировать в него исходные коды проекта bastet. Вы
можете использовать подключение сетевого каталога в хостовой операционной системе для передачи
архива с исходными кодами в виртуальную машину. Далее следует распаковать архив до появления
каталога с исходными файлами (в каталоге должен отображаться Makefile). После этого соберите
пакет bastet и запустите его, чтобы удостовериться, что он правильно собрался. Затем модифицируйте
Makefile, добавив в него раздел install. Обеспечьте при установке копирование исполняемого файла в
/usr/bin с установкой соответствующих прав доступа. Выполните установку и проверьте, что любой
пользователь может запустить установленный пакет.
    
    ```bash
    mkdir ~/files
    cd ~/files
    wget -O bastet-0.43.tgz "https://drive.google.com/uc?export=download&id=1LE6m1N-L9Hct-_7o8sT6yt36d5qINvXr"
    wget -O checkinstall-1.6.2-3.el6.1.x86_64.rpm "https://drive.google.com/uc?export=download&id=140vx_oVN7S0iQQpWb1Zzc5Nbat6lgWTJ"
    wget -O fortunes-ru_1.52-2_all.deb "https://drive.google.com/uc?export=download&id=13FeNRsvntSF_QDBtUFcP8aBIWjwaXh26"
    ```

    ```bash
    mkdir ~/bastet
    cd ~/bastet
    cp ~/files/bastet-0.43.tgz ~/bastet/
    tar -xvzf bastet-0.43.tgz

    cd ~/bastet/bastet-0.43

    yum install boost-devel
    yum install ncurses-devel

    make

    ./bastet
    # копируем строки из INSTALL в Makefile с небольшими изменениями
    make install
    ```

3. Создайте файл task3.log, в который выведите список всех установленных пакетов.

    ```bash
    dnf list installed > ~/task3.log
    ```

4. Вывод зависимостей

    1. Создайте файл task4_1.log, в который выведите список всех пакетов (зависимостей), необходимых для установки и работы компилятора gcc. 

        ```bash
        dnf repoquery --requires --resolve gcc > task4_1.log
        ```

    2. Создайте файл task4_2.log, в который выведите список
    всех пакетов (зависимостей), установка которых требует установленного пакета libgcc. (libgcc-10-dev)

        ```bash
        dnf repoquery --whatrequires libgcc > task4_2.log
        ```

5. Создайте каталог localrepo в домашнем каталоге пользователя root и скопируйте в него пакет
checkinstall-1.6.2-3.el6.1.x86_64.rpm, приложенный к методическим указаниям. 
Создайте собственный локальный репозиторий с именем localrepo из получившегося каталога с пакетом


    ```bash
    yum install createrepo

    sudo mkdir /root/localrepo
    sudo cp ~/files/checkinstall-1.6.2-3.el6.1.x86_64.rpm /root/localrepo/checkinstall-1.6.2-3.e16.1.x86_64.rpm

    sudo createrepo /root/localrepo

    nano /etc/yum.repos.d/localrepo.repo
    
    # [localrepo]
    # name=localrepo
    # baseurl=file:///root/localrepo
    # mirrorlist=file:///root/localrepo
    # enabled=1
    # gpgcheck=0

    ```

6. Создайте файл task6.log, в который выведите список всех доступных репозиториев.

    ```bash
    yum repolist > task6.log
    ```

7. Настройте систему на работу только с созданным локальным репозиторием (достаточно переименовать
конфигурационные файлы других репозиториев). Выведите на экран список доступных для установки
пакетов и убедитесь, что доступен только один пакет, находящийся в локальном репозитории. Установите
этот пакет.

    ```bash
    mv /etc/yum.repos.d /etc/yum.repos.d_backup
    mkdir /etc/yum.repos.d
    cp /etc/yum.repos.d_backup/local.repo /etc/yum.repos.d/

    yum repolist
    yum install checkinstall
    ```

8. Скопируйте в домашний каталог пакет fortunes-ru_1.52-2_all, приложенный к методическим
рекомендациям, преобразуйте его в rpm пакет и установите.

    ```bash
    yum install epel-release
    yum install alien
    alien -r ~/files/fortunes-ru_1.52-2_all.deb
    yum install fortunes-ru-1.52-3.noarch.rpm
    ```

9. Скачайте из сетевого репозитория пакет nano. Пересоберите пакет таким образом, чтобы после его
установки менеджером пакетов, появлялась возможность запустить редактор nano из любого каталога,
введя команду newnano.

    ```bash
    yumdownloader —source nano
    rpm -iv ~/nano-*
    
    nano ~/rpmbuild/SPECS/nano.spec
    # редактируем строчку  

    rmbuild -bb nano.spec
    yum install ~/rpmbuild/RPMS/x86_64/nano-*
    ```




    




