# Основной README файл.
1. [ДЗ №1](https://github.com/RustamDavl/MyGoods/pull/1) `Ссылка указана на Pull Request`
2. [ДЗ №2](https://github.com/RustamDavl/MyGoods/pull/2) `Ссылка указана на Pull Request`
3. [ДЗ №3](https://github.com/RustamDavl/MyGoods/pull/3) `Ссылка указана на Pull Request`
4. [ДЗ №4](https://github.com/RustamDavl/MyGoods/pull/4) `Ссылка указана на Pull Request`
5. [ДЗ №5](https://github.com/RustamDavl/MyGoods/pull/5) `Ссылка указана на Pull Request`

# MyGoods

## Функциональность

## Использование

### API

| Method | URI                                                              | Description                                                      |
|--------|------------------------------------------------------------------|------------------------------------------------------------------|
| get    | /api/v1/goods                                                    | получение всех товаров (начиная с t1)                            |
| get    | /api/v1/goods?name=ab&price=500&op=<&inStock=true&page=2&size=30 | получение всех товаров по фильтрации с пагинацией (начиная с t4) |
| post   | /api/v1/goods                                                    | добавить товар (начиная с t1)                                    |
| put    | /api/v1/goods                                                    | обновить товар (начиная с t1)                                    |
| delete | /api/v1/goods/{id}                                               | удалить товар по идентификатору (начиная с t1)                   |
| get    | /api/v1/delivery                                                 | отображает список документов поставки (начиная с t5)             |
| post   | /api/v1/delivery                                                 | создать документ поставки (начиная с t5)                         |
| get    | /api/v1/delivery/{id}                                            | отображает документ поставки по id (начиная с t5)                |
| delete | /api/v1/delivery/{id}                                            | удалить документ поставки по id (начианя с t5)                   |
| put    | /api/v1/delivery                                                 | обновить значения в документе поставки (начиная с t5)            |
| get    | /api/v1/sale                                                     | получить список документов продаж (начиная с t5)                 |
| get    | /api/v1/sale/{id}                                                | получить документ по id (начиная с t5)                           |
| delete | /api/v1/sale/{id}                                                | удалить документ по id (начиная с t5)                            |
| put    | /api/v1/sale                                                     | обновить значения в документе (начиная с t5)                     |
| post   | /api/v1/sale                                                     | создать документ продали (начиная с t5)                          |
