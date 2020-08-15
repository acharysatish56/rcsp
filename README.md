## rcsp (Rabobank Customer Statement Processor)

### Used
  * Java 1.8v
  * Mavan as Build tool
  * Spring 2.3.2.RELEASE
  * Lombok (moved 1.14.12 to 1.18.12 due to compatibility issue)
  * Junit 5 
  * Swagger
  * Logger(Slf4j)
  * Functional Style Programming

### Instruction for running the application
  * mvn install
  * mvn build
  * mvn package (will generate jar file in target folder)
  * java -jar rcsp-0.0.1-SNAPSHOT.jar

#### INPUT JSON

This following structure is used for input model

```bash
[
  {
    "accNumber": "string",
    "description": "string",
    "endBalance": 0,
    "mutation": "either 12 or -12",
    "startBalance": 0,
    "transNumber": 0
  }
]
```
#### RESPONSE : SUCCESSFUL

```python
input  :
[
  {
    "accNumber": "123",
    "description": "string",
    "endBalance": 2,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 102
  },
  {
    "accNumber": "1234",
    "description": "string",
    "endBalance": 2,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 101
  }
]
output :
{
  "result": "SUCCESSFUL",
  "errorRecords": []
}
```
#### RESPONSE : BAD_REQUEST

```python
input  :
[
  {}{}
]

output :
  "result": "BAD_REQUEST",
  "errorRecords": []
}
```
#### RESPONSE : INTERNAL_SERVER_ERROR

```python
input  :
[
  {
    "accNumber": "123",
    "description": "string",
    "endBalance": 3,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 102
  },
  {
    "accNumber": "1234",
    "description": "string",
    "endBalance": 2,
    "mutation": 1,
    "startBalance": null,
    "transNumber": 101
  }
]
output :
{
  "result": "INTERNAL_SERVER_ERROR",
  "errorRecords": []
}
```
#### RESPONSE : INCORRECT_END_BALANCE

```python
input :
[
  {
    "accNumber": "123",
    "description": "string",
    "endBalance": 3,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 102
  },
  {
    "accNumber": "1234",
    "description": "string",
    "endBalance": 2,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 101
  }
]
output :
{
  "result": "INCORRECT_END_BALANCE",
  "errorRecords": [
    {
      "reference": "reference_of_error_record",
      "accountNumber": "string"
    }
  ]
}
```
#### RESPONSE : DUPLICATE_REFERENCE_INCORRECT_END_BALANCE

```python
input :
[
  {
    "accNumber": "123",
    "description": "string",
    "endBalance": 3,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 101
  },
  {
    "accNumber": "1234",
    "description": "string",
    "endBalance": 2,
    "mutation": 1,
    "startBalance": 1,
    "transNumber": 101
  }
]
output :
{
  "result": "DUPLICATE_REFERENCE_INCORRECT_END_BALANCE",
  "errorRecords": [
    {
      "reference": "reference_of_error_record",
      "accountNumber": "1234"
    },
    {
      "reference": "reference_of_inCorrectEndBalance_record",
      "accountNumber": "123"
    }
  ]
}
```

## contributor
D. Satish Kumar Achary. @mail satish.achary1991@gmail.com
