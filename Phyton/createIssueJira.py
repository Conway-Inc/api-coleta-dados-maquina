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

auth = HTTPBasicAuth("conway.sptech@gmail.com", "ATATT3xFfGF0u_iYYkm-5bGWQ32KNF1G_ToopoPO47oYMVOzY6b37BnzhEPP8ymSW39LvqtoZ8bd3P1dXitqcb6FtOXRWTiGf2vr4dr85Z0tqIX2wHS0VcsFmj3tjJs0KEylwgdt0IXsnGkT5rZ9QAYVqkjkvpRbzGFlRpxBEVG25W2q2QhM-wk=4FDEDBCE")

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