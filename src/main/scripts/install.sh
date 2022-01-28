#!/bin/bash

echo "Начинаем настройку форума..."

mv *.service /etc/systemd/system/forum.service

systemctl daemon-reload

echo -e "\033[32mПервоначальная настройка закончена, давай создадим админа\033[0m"

read -r -p 'Введите имя: ' name_admin

while [ -z "$name_admin" ]
do
    echo -e "\033[31mИмя не должно быть пустое\033[0m"
    read -r -p 'Введите имя: ' name_admin
done

echo "Теперь давай введем пароль"
read -r -sp 'Введите пароль: ' pass_admin

while [ -z "$pass_admin" ]
do
    echo
    echo -e "\033[31mПароль не должен быть пустым\033[0m"
    read -r -sp 'Введите пароль: ' pass_admin
done

echo
add_admin_to_bd=$(psql -U postgres -d forum -c "INSERT INTO usr (id, active, password, username) VALUES (1, true, '$pass_admin', '$name_admin')")

add_admin_role=$(psql -U postgres -d forum -c "INSERT INTO user_role (user_id, roles) VALUES (1, 'ADMIN')")
add_user_role=$(psql -U postgres -d forum -c "INSERT INTO user_role (user_id, roles) VALUES (1, 'USER')")
add_moderator_role=$(psql -U postgres -d forum -c "INSERT INTO user_role (user_id, roles) VALUES (1, 'MODERATOR')")

echo -e "\033[32mАдмин с именем ""$name_admin"" успешно добавлен на форум\033[0m"