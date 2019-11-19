export FOLDER=/home/vkarri/HMS/utilities/*.java
for entry in $FOLDER
do
  javac $entry
  echo $entry
done