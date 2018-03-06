package com.agt.bsuirgek.client.network;

public interface AppConfig {

    String BASE_URL = "http://37.187.116.151:8080/trimple/";

    String TEST_JSON = "[\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"1\",\n" +
            "      \"surname\":\"Гурский\",\n" +
            "      \"name\":\"Владимир\",\n" +
            "      \"patronymic\":\"Евгеньевич \",\n" +
            "      \"data\":\"к. т. н. , доцент БГУ\",\n" +
            "      \"phone\":\"+375297624881\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"1Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"1List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Авраменко\",\n" +
            "      \"name\":\"С.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Антропов\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Бойко\",\n" +
            "      \"name\":\"М.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Васильев\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Вашко\",\n" +
            "      \"name\":\"Ю.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Велесюк\",\n" +
            "      \"name\":\"М.\",\n" +
            "      \"patronymic\":\"Л.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Верещаго\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"Ю.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Ворушенко\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List7Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Гайшун\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List8Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Грабок\",\n" +
            "      \"name\":\"С.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"1List9Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"2\",\n" +
            "      \"surname\":\"Коваленко\",\n" +
            "      \"name\":\"Валентин\",\n" +
            "      \"patronymic\":\"Максимович\",\n" +
            "      \"data\":\"доцент кафедры ТОЭ БГУИР, к. т. н. , доцент\",\n" +
            "      \"phone\":\"м. т. : 80295007385\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"2Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"2List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Денисенко\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Денисов\",\n" +
            "      \"name\":\"Е.\",\n" +
            "      \"patronymic\":\"М.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Дешко\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"Ю.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Дещеня\",\n" +
            "      \"name\":\"П.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Доманцевич\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Дубровский\",\n" +
            "      \"name\":\"П.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Дулик\",\n" +
            "      \"name\":\"М.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Елисеев\",\n" +
            "      \"name\":\"И.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"2List7Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"3\",\n" +
            "      \"surname\":\"Курулёв\",\n" +
            "      \"name\":\"Александр\",\n" +
            "      \"patronymic\":\"Петрович\",\n" +
            "      \"data\":\"профессор кафедры ТОЭ БГУИР, к. т. н. , доцент\",\n" +
            "      \"phone\":\"м. т. : 80292617477\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"3Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"3List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Жолнеркевич\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"И.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Зуйкевич\",\n" +
            "      \"name\":\"И.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Иванов\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Иванов\",\n" +
            "      \"name\":\"С.\",\n" +
            "      \"patronymic\":\"О.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Кацук\",\n" +
            "      \"name\":\"Д.\",\n" +
            "      \"patronymic\":\"Т.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Качан\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"И.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Качура\",\n" +
            "      \"name\":\"Н.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Киреева\",\n" +
            "      \"name\":\"Н.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"3List7Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"4\",\n" +
            "      \"surname\":\"Свито\",\n" +
            "      \"name\":\"Игорь\",\n" +
            "      \"patronymic\":\"Леонтьевич\",\n" +
            "      \"data\":\"к. т. н. , доцент кафедры ТОЭ БГУИР, \",\n" +
            "      \"phone\":\"р. т. : 293 86 08\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"4Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"4List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Кирилович\",\n" +
            "      \"name\":\"М.\",\n" +
            "      \"patronymic\":\"Д.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Ковалевский\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Костив\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Костюкова\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"Р.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Красов\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Кришнева\",\n" +
            "      \"name\":\"Н.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Кудрявцев\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"4List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"5\",\n" +
            "      \"surname\":\"Сергеев\",\n" +
            "      \"name\":\"Владимир\",\n" +
            "      \"patronymic\":\"Игнатьевич\",\n" +
            "      \"data\":\"доцент кафедры информационных технологий БГУ, к. т. н.\",\n" +
            "      \"phone\":\"р. т. : 209 59 28\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"5Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"5List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Кузнечик\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Лапко\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Лебедик\",\n" +
            "      \"name\":\"К.\",\n" +
            "      \"patronymic\":\"М.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Левдиков\",\n" +
            "      \"name\":\"М.\",\n" +
            "      \"patronymic\":\"И.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Липский\",\n" +
            "      \"name\":\"О.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Манько\",\n" +
            "      \"name\":\"Л.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Масляков\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"5List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"6\",\n" +
            "      \"surname\":\"Царик\",\n" +
            "      \"name\":\"Сергей\",\n" +
            "      \"patronymic\":\"Всеволодович\",\n" +
            "      \"data\":\"доцент кафедры информационных технологий БГУ, к. т. н. , доцент\",\n" +
            "      \"phone\":\"Vel. : 80296550009\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"6Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"6List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Меерович\",\n" +
            "      \"name\":\"П.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Микалуцкий\",\n" +
            "      \"name\":\"К.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Михалап\",\n" +
            "      \"name\":\"О.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Михалёв\",\n" +
            "      \"name\":\"П.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Нагорный\",\n" +
            "      \"name\":\"И.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Новицкий\",\n" +
            "      \"name\":\"И.\",\n" +
            "      \"patronymic\":\"О.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Орлов\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"6List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"7\",\n" +
            "      \"surname\":\"Белодед\",\n" +
            "      \"name\":\"Николай\",\n" +
            "      \"patronymic\":\"Иванович\",\n" +
            "      \"data\":\"к. т. н. , доцент Академии управления при Президенте РБ,\",\n" +
            "      \"phone\":\"+375296702710\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"7Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"7List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Петроченко\",\n" +
            "      \"name\":\"М.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Попов\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Проказов\",\n" +
            "      \"name\":\"Д.\",\n" +
            "      \"patronymic\":\"Р.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Пухальский\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"Э.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Рачок\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Романчук\",\n" +
            "      \"name\":\"Д.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Севрюк\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"Э.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"7List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"8\",\n" +
            "      \"surname\":\"Щербович\",\n" +
            "      \"name\":\"Жанна\",\n" +
            "      \"patronymic\":\"Ивановна\",\n" +
            "      \"data\":\"старший преподаватель Академии управления при Президенте РБ,\",\n" +
            "      \"phone\":\"+375295595658\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"8Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"8List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Сезень\",\n" +
            "      \"name\":\"С.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Сергута\",\n" +
            "      \"name\":\"Д.\",\n" +
            "      \"patronymic\":\"Г.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Слугин\",\n" +
            "      \"name\":\"С.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Стасевич\",\n" +
            "      \"name\":\"К.\",\n" +
            "      \"patronymic\":\"Ю.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Субоч\",\n" +
            "      \"name\":\"Д.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Ткаченко\",\n" +
            "      \"name\":\"Ю.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Трухан\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"8List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"n\":\"9\",\n" +
            "      \"surname\":\"Юрча\",\n" +
            "      \"name\":\"Ирина\",\n" +
            "      \"patronymic\":\"Александровна\",\n" +
            "      \"data\":\"старший преподаватель Академии управления при Президенте РБ,\",\n" +
            "      \"phone\":\"+375297734843\"\n" +
            "    },\n" +
            "    \"type\":\"Teacher\",\n" +
            "    \"id\":\"9Teacher\",\n" +
            "    \"links\":[\n" +
            "      \"9List\"\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "      \"links\":\"List\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Харитончик\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List0Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Ходорович\",\n" +
            "      \"name\":\"Н.\",\n" +
            "      \"patronymic\":\"Н.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List1Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Цивинский\",\n" +
            "      \"name\":\"В.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List2Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Циунчик\",\n" +
            "      \"name\":\"Е.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List3Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Шикунец\",\n" +
            "      \"name\":\"Е.\",\n" +
            "      \"patronymic\":\"В.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List4Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Кравченко\",\n" +
            "      \"name\":\"Н.\",\n" +
            "      \"patronymic\":\"А.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List5Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"params\":{\n" +
            "      \"surname\":\"Моисеенков\",\n" +
            "      \"name\":\"А.\",\n" +
            "      \"patronymic\":\"С.\"\n" +
            "    },\n" +
            "    \"type\":\"Student\",\n" +
            "    \"id\":\"9List6Student\",\n" +
            "    \"links\":[\n" +
            "\n" +
            "    ],\n" +
            "    \"tags\":{\n" +
            "\n" +
            "    }\n" +
            "  }\n" +
            "]";

}
