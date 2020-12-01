#!/usr/bin/python
# encoding=utf-8
import sys
import json
sys.path.append('..')
import apis.aep_device_command

if __name__ == '__main__':

    appKey = 'GPvZaoo8eU2'
    appSecret = '31nw67zu6O'
    MasterKey = '1943e0abf0fb45afb2cbd7b290674bbf'

    body = {}
    # content
    body["content"] = {}
    # cotent-params
    body["content"]["params"] = {}
    body["content"]["params"]["control_int"] = 1
    # content-serviceIdentifier
    body["content"]["serviceIdentifier"] = "motor_control"

    body["deviceId"] = "9a0aff904d3a4f3382388eaf0d3b39de"
    body["operator"] = "fjj"
    body["productId"] = 15011727
    body["ttl"] = 7200
    body["deviceGroupId"] = 100
    body["level"] = 1
    body_str = json.dumps(body, ensure_ascii=False)

    # body
    # {
    #     "content": {
    #         "params":
    #             {
    #                 "control_int": 1
    #             },
    #         "serviceIdentifier": "motor_control"
    #     },
    #     "deviceId": "9a0aff904d3a4f3382388eaf0d3b39de",
    #     "operator": "zzt",
    #     "productId": 15011727,
    #     "ttl": 7200,
    #     "deviceGroupId": 100,
    #     "level": 1
    # }

    print(body_str)
    result = apis.aep_device_command.CreateCommand(appKey, appSecret, MasterKey, body_str)
    print(type(result))
    print('result=' + str(result))

    jsonR = json.loads(result)
    if (jsonR["code"] == 0 and jsonR["msg"] == "ok"):
        sys.exit(0)
    else:
        sys.exit(jsonR["code"])