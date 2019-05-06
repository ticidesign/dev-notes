# Switching from v3.4 to latest

select_lastest() {
  # Stop and unlink 3.4
  brew services stop mongodb@3.4
  brew unlink mongodb@3.4

  # Link last version
  brew unlink mongodb && brew link mongodb
  # Update mongo logs and database paths
  sed -i.3.4 's:/mongodb-3\.4/:/mongodb/:' /usr/local/etc/mongod.conf
  # Restart the service
  brew services restart mongodb

}

select_34() {
  # Stop and Unlink
  brew services stop mongodb
  brew unlink mongodb

  # Link 3.4
  brew unlink mongodb@3.4 && brew link mongodb@3.4 --force
  # Update mongo logs and database paths
  sed -i.latest 's:/mongodb/:/mongodb-3\.4/:' /usr/local/etc/mongod.conf
  # Restart the service
  brew services restart mongodb@3.4
}

if [ $1 == 'latest' ]
then
  select_lastest
elif [ $1 == '3.4' ]
then
  select_34
else
  printf Passed: $1
  printf Usage: mongo-use [latest|3.4]
fi

printf Current mongo version:
mongo --version
