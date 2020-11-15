import mysql.connector
import pymysql


def Init():
    # db = mysql.connector.connect(
    db = pymysql.connect(
        host='localhost',
        user='root',
        passwd='',
        database="stu"
    )

    myCursor = db.cursor()

    # students
    myCursor.execute("DROP TABLE IF EXISTS students")
    myCursor.execute("CREATE TABLE students("
                     "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, "
                     "student_id INT NOT NULL, "
                     "student_name VARCHAR(255))")
    # student_course
    myCursor.execute("DROP TABLE IF EXISTS student_course")
    myCursor.execute("CREATE TABLE student_course("
                     "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                     "student_id INT NOT NULL,"
                     "course_id INT NOT NULL)")
    # courses
    myCursor.execute("DROP TABLE IF EXISTS courses")
    myCursor.execute("CREATE TABLE courses("
                     "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                     "course_id INT NOT NULL, "
                     "course_name VARCHAR(255) NOT NULL, "
                     "course_time TIME NOT NULL, "
                     "course_day INT NOT NULL)")

    # test data
    s_data = "INSERT INTO students(student_id, student_name) VALUES " \
             "(100, 'John'), " \
             "(101, 'Emmy'), " \
             "(102, 'Hannah'), " \
             "(103, 'David');"

    c_data = "INSERT INTO courses(course_id, course_name, course_time, course_day) VALUES " \
             "(37910, 'Database','3:30',2), " \
             "(37905, 'Network','17:00',2), " \
             "(37900, 'Data Structure','18:30',2)," \
             "(37777, 'Talk','12:30',1);"

    s_c_data = "INSERT INTO student_course(student_id, course_id) VALUES " \
               "(101, 37910), " \
               "(101, 37905), " \
               "(101, 37900)," \
               "(102, 37900)," \
               "(103, 37910)," \
               "(100, 37777);"

    myCursor.execute(s_data)
    myCursor.execute(c_data)
    myCursor.execute(s_c_data)
    db.commit()
    db.close()
