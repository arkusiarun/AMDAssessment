# AMDAssessment

All the Outputs Can be Found in output directory.

1.     Application to generate an RSA key pair, key size = 2048 bits (Standard Java library can be used to generate all keys and hashes).
             RSA  2048  bit  Private and Public Key Generated
             OutputFileName: private_rsa.txt, public_rsa.txt

2.     Application to generate SHA-256 hash of the attached file. No signing is needed. Just print hash in HEX encoding to standard output.
             Hash  of the File Can be Found in File: sha_hex.txt

3.     Application that will encrypt and decrypt the file using the RSA key pair generated.
             With Original file as Reference  encrypted file is created
             Encrypted file as input Decrypted file is Created, so original file can be generated.
             Original File Name: imageFile.png
             Encrypted File Name: encryptedFile
             Decrypted File Name: decryptedFile.jpg

Steps For Encrypting and Decrypting File:
1. Read File from Resource Folder.
2. Since RSA won't be able to encrypt or decrypt large file AES is Used.
3. Token used for AES encryption is encrypted and Decrypted with RSA.
4. With  a Constant Token File is Encrypted  and saves as encryptedFile in output Directory.
5. Now this token is encrypted using RSA and saved to a file in  Base64 encoded Format in aesEncryptionKet.text.
6. During Decryption Token is read from File and  with RSA public key is decrypted.
7. Once Actual token is generated with source file as previously generated encryptedFile decrypted file with name decryptedFile.png is generated.