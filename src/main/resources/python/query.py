# encoding=utf-8
import apis.aep_device_status
import sys
import json

sys.path.append('')

def show():
    #params = '{"productId": "15011727", "deviceId": "9a0aff904d3a4f3382388eaf0d3b39de", "datasetId": "temperature_data"}'
    #result = apis.aep_device_status.QueryDeviceStatus('GPvZaoo8eU2', '31nw67zu6O', params)
    #print('result='+str(result)+"\n")

    params = '{"productId": "15011727", "deviceId": "9a0aff904d3a4f3382388eaf0d3b39de"}'
    result = apis.aep_device_status.QueryDeviceStatusList('GPvZaoo8eU2', '31nw67zu6O', params)
    jsonR = json.loads(result)
    print('result='+str(result)+"\n")

    temperature = -1;
    lists = jsonR['deviceStatusList']
    for list in lists:
        if list['datasetId'] == 'temperature_data':
            temperature = list['value']
    exit = int(float(temperature) * 10)
    sys.exit(exit)

    #params = [{"productId": "10000088", "deviceId": "10000088001", "begin_timestamp": "1538981624878", "end_timestamp": "1539575396505"}]
    #data = json.dumps(params)
    #print(data)

    #params = '{"productId": "15011727", "deviceId": "9a0aff904d3a4f3382388eaf0d3b39de", "begin_timestamp": "1606038840211", "end_timestamp": "1606038850211"}'
    #result = apis.aep_device_status.getDeviceStatusHisInTotal('GPvZaoo8eU2', '31nw67zu6O', params)
    #print('result='+str(result)+"\n")

    #result = apis.aep_device_status.getDeviceStatusHisInPage('GPvZaoo8eU2', '31nw67zu6O', '{}')
    #print('result='+str(result)+"\n")

if __name__ == '__main__':
    show()