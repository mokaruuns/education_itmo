# # Тема 20. Классы и обьекты (ООП)
#
# class Student:
#     name = "Roman"
#
#     def display_info(self):
#         print("Привет, меня зовут", self.name)
#
#
# student1 = Student()
# student1.display_info()
#
# student2 = Student()
# student2.name = "Ivan"
# student2.display_info()
#
# student3 = Student()
# student3.name = "Evgeny"
# student3.display_info()


class Predmet:

    def __init__(self, name_predmet):
        self.name = name_predmet

    def display_info(self):
        print("Мне нравится предмет", self.name)


student1 = Predmet("Русский")
student1.display_info()
student2 = Predmet("Математика")
student2.display_info()
