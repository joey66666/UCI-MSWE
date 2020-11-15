# coding = utf-8
# !/usr/bin/python3
import DB_Init
import pymysql
import threading
import tkinter as tk
import tkinter.font as tkFont


def connect_db():
    db = pymysql.connect(
        host='localhost',
        user='root',
        passwd='',
        database="stu"
    )
    return db


def day2course_day(i):
    course_day = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
    return course_day[i]


def GUI():
    # location variables
    y1 = 50
    y2 = y1 + 50
    y3 = y2 + 50
    y4 = y3 + 50
    y5 = y4 + 50
    y6 = y5 + 50
    x0 = 70

    window = tk.Tk()
    window.title("Courses Management")
    window.geometry('1000x600')

    tk.Label(window, text="Exercise M5.1 - Students Courses Management System",
             font=tkFont.Font(size=20, weight=tkFont.BOLD)).pack()
    frame = tk.Frame()
    frame.pack()
    textarea = tk.Text(frame, width=135, height=22)
    frame.place(x=20, y=y6)
    scrollbar = tk.Scrollbar(frame, orient='vertical')
    scrollbar.config(command=textarea.yview)
    scrollbar.pack(side='right', fill='y')
    # textarea.config(yscrollcommand=scrollbar.set)
    textarea.pack(side='left', fill='both', expand=1)
    textarea.config(state='normal')
    textarea.delete(1.0, 'end')

    db = connect_db()
    myCursor = db.cursor()

    def enroll_student():
        student_id_input = 0
        try:
            student_id_input = int(student_id.get())
        except ValueError:
            textarea.insert('end', "Student ID Invalid\n\n")
            return

        student_name_input = student_name.get()
        if (len(student_name_input)) == 0:
            textarea.insert('end', "Student's Name can not be empty\n\n")
            return

        myCursor.execute("INSERT INTO students(student_id, student_name) VALUES (%d, '%s')" % (
            student_id_input, student_name_input))
        db.commit()
        textarea.insert('end', "Student Enrolled! Student ID: %d, Student Name: %s\n\n" % (
            student_id_input, student_name_input))
        # textarea.config(state='disable')

    def introduce_course():
        course_id_input = 0
        day_input = 0
        try:
            course_id_input = int(course_id.get())
        except ValueError:
            textarea.insert('end', "Course ID Invalid\n\n")
            return
        try:
            day_input = int(day.get())
        except ValueError:
            textarea.insert('end', "Day Invalid\n\n")
            return

        course_name_input = course_name.get()
        time_input = time.get()
        myCursor.execute("INSERT INTO courses(course_id, course_name, course_time, course_day) "
                         "VALUES (%d, '%s','%s',%d)" % (
                             course_id_input, course_name_input, time_input, day_input))
        db.commit()
        textarea.insert('end',
                        "Course Introduced! Course ID: %d, Course Name: %s, course_time: %s,course_day: %d\n\n" % (
                            course_id_input, course_name_input, time_input, day_input))

    def enroll_course():
        try:
            student_id_input = int(student_id2.get())
        except ValueError:
            textarea.insert('end', "Student ID Invalid\n\n")
            return
        try:
            course_id_input = int(course_id2.get())
        except ValueError:
            textarea.insert('end', "Course ID Invalid\n\n")
            return

        myCursor.execute("INSERT INTO student_course(student_id, course_id)"
                         "VALUES (%d ,%d)" % (student_id_input, course_id_input))
        db.commit()
        textarea.insert('end', "Student Enrolled In Course! Course ID: %d, Student ID: %d\n\n" % (
            course_id_input, student_id_input))

    def query_students():
        student_name_input = student_name3.get()
        course_day_input = 0
        if len(course_day.get()) != 0:
            try:
                course_day_input = int(course_day.get())
            except ValueError:
                textarea.insert('end', "Course Day Invalid\n\n")
                return

        if len(student_id3.get()) == 0 and len(student_name3.get()) == 0:
            if len(course_day.get()) == 0:
                sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id JOIN " \
                      "courses ON student_course.course_id = courses.course_id "
            else:
                sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id JOIN " \
                      "courses ON student_course.course_id = courses.course_id WHERE course_day = %d" % (
                          course_day_input)
        elif len(student_id3.get()) == 0 and len(student_name_input) != 0:
            if len(course_day.get()) == 0:
                sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id JOIN " \
                      "courses ON student_course.course_id = courses.course_id WHERE student_name LIKE '%s'" % (
                          student_name_input)
            else:
                sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id JOIN " \
                      "courses ON student_course.course_id = courses.course_id WHERE student_name LIKE '%s' AND course_day = %d" % (
                          student_name_input, course_day_input)

        elif len(student_name3.get()) == 0 and len(student_id3.get()) != 0:
            try:
                student_id_input = int(student_id3.get())
            except ValueError:
                textarea.insert('end', "Student ID Invalid\n\n")
                return
            else:
                if len(course_day.get()) == 0:
                    sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id " \
                          "JOIN courses ON student_course.course_id = courses.course_id WHERE students.student_id = %d" % (
                              student_id_input)
                else:
                    sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id " \
                          "JOIN courses ON student_course.course_id = courses.course_id WHERE students.student_id = %d AND course_day = %d" % (
                              student_id_input, course_day_input)
        else:
            try:
                student_id_input = int(student_id3.get())
            except ValueError:
                textarea.insert('end', "Student ID Invalid\n\n")
                return
            else:
                if len(course_day.get()) == 0:
                    sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id " \
                          "JOIN courses ON student_course.course_id = courses.course_id WHERE students.student_id = %d " \
                          "AND students.student_name LIKE %s" % (
                              student_id_input, student_name_input)
                else:
                    sql = "SELECT * FROM students JOIN student_course ON students.student_id = student_course.student_id " \
                          "JOIN courses ON student_course.course_id = courses.course_id WHERE students.student_id = %d " \
                          "AND students.student_name LIKE %s AND course_day = %d" % (
                              student_id_input, student_name_input, course_day_input)
        sql += " ORDER BY students.student_id;"
        myCursor.execute(sql)
        results = myCursor.fetchall()
        if len(results) != 0:

            for row in results:
                s_id = row[1]
                s_name = row[2]
                c_id = row[7]
                c_name = row[8]
                c_time = row[9]
                c_day = row[10]
                c_time_str = str(c_time)
                c_day_str = day2course_day(c_day)
                textarea.insert('end',
                                "Student ID: %d, Student Name: %s, Course ID: %d, Course Name: %s, Course Time: %s, "
                                "Course Day: %s\n" % (
                                    s_id, s_name, c_id, c_name, c_time_str, c_day_str))
            textarea.insert('end', "\n")
        else:
            textarea.insert('end', "No result!\n")

    def query_courses():
        course_name_input = course_name4.get()
        course_id_input = 0
        if len(course_id4.get()) != 0:
            try:
                course_id_input = int(course_id4.get())
            except ValueError:
                textarea.insert('end', "Course ID Invalid\n\n")
                return

        if len(course_name_input) == 0 and len(course_id4.get()) == 0:
            sql = "SELECT * FROM courses JOIN student_course ON courses.course_id = student_course.course_id JOIN " \
                  "students ON student_course.student_id = students.student_id"


        elif len(course_id4.get()) != 0 and len(course_name_input) == 0:
            sql = "SELECT * FROM courses JOIN student_course ON courses.course_id = student_course.course_id JOIN " \
                  "students ON student_course.student_id = students.student_id WHERE courses.course_id = %d" % (
                      course_id_input)


        elif len(course_name_input) != 0 and len(course_id4.get()) == 0:
            sql = "SELECT * FROM courses JOIN student_course ON courses.course_id = student_course.course_id JOIN " \
                  "students ON student_course.student_id = students.student_id WHERE courses.course_name LIKE '%s'" % (
                      course_name_input)
        else:
            sql = "SELECT * FROM courses JOIN student_course ON courses.course_id = student_course.course_id JOIN " \
                  "students ON student_course.student_id = students.student_id WHERE courses.course_id = %d AND courses.course_name LIKE '%s'" % (
                      course_id_input, course_name_input)

        sql += " ORDER BY courses.course_id;"
        myCursor.execute(sql)
        results = myCursor.fetchall()
        if len(results) != 0:
            for row in results:
                c_id = row[1]
                c_name = row[2]
                c_time = row[3]
                c_day = row[4]
                s_id = row[9]
                s_name = row[10]
                c_time_str = str(c_time)
                c_day_str = day2course_day(c_day)
                textarea.insert('end',
                                "Student ID: %d, Student Name: %s, Course ID: %d, Course Name: %s, Course Time: %s, "
                                "Course Day: %s\n" % (
                                    s_id, s_name, c_id, c_name, c_time_str, c_day_str))
            textarea.insert('end', "\n")
        else:
            textarea.insert('end', "No result!\n\n")

    student_id = tk.StringVar()
    student_name = tk.StringVar()
    tk.Label(window, text="Student ID").place(x=x0 + 20, y=y1)
    tk.Entry(window, textvariable=student_id).place(x=x0 + 100, y=y1, width=100)
    tk.Label(window, text="Student Name").place(x=x0 + 300, y=y1)
    tk.Entry(window, textvariable=student_name).place(x=x0 + 400, y=y1, width=100)
    tk.Button(text='Enroll', command=enroll_student).place(x=x0 + 700, y=y1)

    course_id = tk.StringVar()
    course_name = tk.StringVar()
    time = tk.StringVar()
    day = tk.StringVar()
    tk.Label(window, text="Course ID").place(x=x0 + 20, y=y2)
    tk.Entry(window, textvariable=course_id).place(x=x0 + 90, y=y2, width=80)
    tk.Label(window, text="Course Name").place(x=x0 + 180, y=y2)
    tk.Entry(window, textvariable=course_name).place(x=x0 + 270, y=y2, width=80)
    tk.Label(window, text="Time").place(x=x0 + 360, y=y2)
    tk.Entry(window, textvariable=time).place(x=x0 + 400, y=y2, width=80)
    tk.Label(window, text="Day").place(x=x0 + 490, y=y2)
    tk.Entry(window, textvariable=day).place(x=x0 + 530, y=y2, width=80)
    tk.Button(text='Introduce', command=introduce_course).place(x=x0 + 690, y=y2)

    student_id2 = tk.StringVar()
    course_id2 = tk.StringVar()
    tk.Label(window, text="Student ID").place(x=x0 + 20, y=y3)
    tk.Entry(window, textvariable=student_id2).place(x=x0 + 100, y=y3, width=100)
    tk.Label(window, text="Course ID").place(x=x0 + 300, y=y3)
    tk.Entry(window, textvariable=course_id2).place(x=x0 + 400, y=y3, width=100)
    tk.Button(text='Enroll', command=enroll_course).place(x=x0 + 700, y=y3)

    course_id4 = tk.StringVar()
    course_name4 = tk.StringVar()
    tk.Label(window, text="Course ID").place(x=x0 + 20, y=y5)
    tk.Entry(window, textvariable=course_id4).place(x=x0 + 100, y=y5, width=100)
    tk.Label(window, text="Course Name").place(x=x0 + 300, y=y5)
    tk.Entry(window, textvariable=course_name4).place(x=x0 + 400, y=y5, width=100)
    tk.Button(text='Query', command=query_courses).place(x=x0 + 700, y=y5)

    student_id3 = tk.StringVar()
    student_name3 = tk.StringVar()
    course_day = tk.StringVar()
    tk.Label(window, text="Student ID").place(x=x0 + 20, y=y4)
    tk.Entry(window, textvariable=student_id3).place(x=x0 + 100, y=y4, width=100)
    tk.Label(window, text="Student Name").place(x=x0 + 220, y=y4)
    tk.Entry(window, textvariable=student_name3).place(x=x0 + 320, y=y4, width=100)
    tk.Label(window, text="Course Day").place(x=x0 + 450, y=y4)
    tk.Entry(window, textvariable=course_day).place(x=x0 + 530, y=y4, width=100)
    tk.Button(text='Query', command=query_students).place(x=x0 + 700, y=y4)

    window.mainloop()


if __name__ == '__main__':
    # Init Database
    DB_Init.Init()

    # Activate
    threadingGUI = threading.Thread(GUI())
    threadingGUI.start()
