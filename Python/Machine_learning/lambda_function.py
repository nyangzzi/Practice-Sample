############함수 이름 없이, 함수처럼 쓸 수 있는 익명 함수###########
############수학의 람다 대수에서 유래##############################
#python3부터는 권장하지 않으나 여전히 많이 쓰인다

f = lambda x,y: x + y
print(f(1,4))
#5

f = lambda x: x**2
print(f(3))
#9

f = lambda x: x / 2
print(f(3))
#1.5

print((lambda x: x + 1)(5))
#6
