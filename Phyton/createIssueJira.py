import requests
from requests.auth import HTTPBasicAuth
import json
from datetime import datetime

# mensagem = {"text": f"""
#                🚨ALERTA🚨

#                 Protocolo  => 837021
                  
#                 """}
# chatLatam = "https://hooks.slack.com/services/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"

# postMsg = requests.post(chatLatam, data=json.dumps(mensagem))


url = "https://conway-airway.atlassian.net/rest/api/3/issue"

auth = HTTPBasicAuth("conway.sptech@gmail.com", "ATATT3xFfGF0MGnHg3aV7o5aoc748baZDn-UvxSw5rYufU0bruWQa0oUKiXqXP7Fs7-UuqAcqWPkyAPi-aZdTNmuUWur8dCGls60CD-AUyNTi-hTr-ZJojNBJKLARMA8AARfWNzkFnBruIHFjzzstcW_D-HH771nRrTRAA05U9eNqAJ-LfJR3Vo=39C66BA5")

headers = {
      "Accept": "application/json",
      "Content-Type": "application/json"
}

payload = json.dumps({
        "fields":{
            "summary": "837021 - ALERTA DO SLACK",
            "project":{"key":"AWLATAM"},
            'issuetype': {'name': 'General request'},
            "description": {"content": [{"content": [
                                          {
                                            "text": "CONFIRA SEUS ALERTAS!!!!",
                                            "type": "text"
                                          }],
                                        "type": "paragraph"}],
                                        "type": "doc",
                                        "version": 1}
          }
    })

response = requests.request(
      "POST",
      url,
      data=payload,
      headers=headers,
      auth=auth
    )

print(json.dumps(json.loads(response.text), sort_keys=True, indent=4, separators=(",", ": ")))