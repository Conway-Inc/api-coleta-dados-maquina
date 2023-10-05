import requests
from requests.auth import HTTPBasicAuth
import json
from datetime import datetime

mensagem = {"text": f"""
               🚨ALERTA🚨

                Protocolo  => 837021
                  
                """}
chatLatam = "https://hooks.slack.com/services/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"

postMsg = requests.post(chatLatam, data=json.dumps(mensagem))


url = "https://conway-airway.atlassian.net/rest/api/3/issue"

auth = HTTPBasicAuth("conway.sptech@gmail.com", "ATATT3xFfGF08ASG2iR-9PT2tOjNZBdLpMc9Dj7gwEdxTRLkB1ZDQ7g2eu4bHHa70DAxlJMMxWXfmofgjZOi0ulwIfDxI2AkMvWurRDVCmuDSVahZtzXk7yCks3VxIokvigYPfhcvX-3TR66LVEJFZOfKKNQdFbquno5Ug3HAFf6g7XzXtBurzs=FD4378DB")

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