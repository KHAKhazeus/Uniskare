package contracts.order.rest
//org.springframework.cloud.contract.spec.Contract.make {
//    description("""
//        try making new orders
//    """)
//    request {
//        method POST()
//        url "/new_order"
//        body(
//            skill_id:
//        )
//    }
//    response {
//
//    }
//}
import org.springframework.cloud.contract.spec.Contract
// test start from 900 (temporal decision)
Contract.make {
    name("should create an order")
    request {
        method 'POST'
        url('/orders/new_order')
        body("""
                {
                    "user_id": "901",
                    "skill_id": 901,
                    "value": 12.2,
                    "order_time": "2019-12-17 10:30:10"
                }
        """)
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""
                    {
                        "timestamp": null,
                        "status": 200,
                        "error": "No error",
                        "message": "insert success",
                        "path": null,
                        "data": null
                    }
                """
        )
        headers {
            contentType(applicationJson())
        }
    }
}
//[
//        Contract.make {
//            name("should create an order")
//            request {
//                method 'POST'
//                url('/orders/new_order')
//                body(
//                        skill_id: 901, user_id: 901, order_time: '2019-12-17 10:30:10', order_value: 12.2
//                )
//                headers {
//                    contentType(applicationJson())
//                }
//            }
//            response {
//                status 200
//                body("""
//                    {
//                        "timestamp": null,
//                        "status": 200,
//                        "error": "No error",
//                        "message": "insert success",
//                        "path": null,
//                        "data": null
//                    }
//                """
//                )
//                headers {
//                    contentType(applicationJson())
//                }
//            }
//        },
//        Contract.make {
//            name("search the order")
//            request {
//                method 'POST'
//                url('/orders/orders_info')
//                body(
//                        user_id: 901, state: 1
//                )
//                headers {
//                    contentType(applicationJson())
//                }
//            }
//            response {
//                status 200
//                body(
//                        data: [[
//                                   skill_cover: $(anyNonEmptyString()),
//                                   price: 12.2,
//                                   order_time: "2019-12-12 18:43:53",
//                                   skiller_name: null,
//                                   skill_title: null
//                               ]]
//                )
//                bodyMatchers {
//                    jsonPath('$.data', byType {
//                        minOccurrence(1)
//                    })
//                    jsonPath('$.data[0].price', byRegex("[-+]?[0-9]*\\.?[0-9]*"))
//                    jsonPath('$.data[0].order_time', byTimestamp())
//                    jsonPath('$.data[0].skiller_name', byNull())
//                    jsonPath('$.data[0].skill_title', byNull())
//                }
//                headers {
//                    contentType(applicationJson())
//                }
//            }
//        }
//]