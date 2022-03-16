#!/bin/bash

regex="\"?([A-Z]+[^\.\n]{50,}\?*)(\.|\?|\")+"

current_number_string=$(cat /root/forum/bot/number_string.txt)
all_strings_from_book=$(cat /root/forum/bot/text.txt | wc -l)
string_number=$current_number_string
text_for_bot_is_empty=$(psql -t -U forum -d forum -c "SELECT count(*) FROM text_for_bot")
counter=0

#the range is specified - from which line and total lines
for i in $(seq "$current_number_string" "$all_strings_from_book");
do
#line with which we continue the algorithm
  text_line=$(sed -n "$i"'p' /root/forum/bot/text.txt)

  string_number=$((string_number + 1))

  if [[ $text_line =~ $regex ]]; then
    
    counter=$((counter + 1))
    
    echo ${BASH_REMATCH[1]}
    
    if [ "$text_for_bot_is_empty" -eq 0 ]; then
      echo "create" 
      add_text_for_bot=$(psql -U forum -d forum -c "INSERT INTO text_for_bot (id, text, used) VALUES ('$counter', '${BASH_REMATCH[1]}', false)")
    else
      echo "update" 
      update_text_for_bot=$(psql -U forum -d forum -c "UPDATE text_for_bot SET text='${BASH_REMATCH[1]}', used=false WHERE id='$counter'")
    fi
    
    if [ "$counter" -eq 10 ]; then
    
#add to the file the number of the line with which we will start next time 
      echo "$string_number" > /root/forum/bot/number_string.txt
      
      break
    fi
  fi
done

#if you reach the end of the book, then start again from line 1
if [ "$string_number" = "$all_strings_from_book" ]; then
  echo "1" > number_string.txt
else
  echo
fi