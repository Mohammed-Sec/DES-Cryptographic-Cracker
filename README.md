DES Cryptographic Cracker
Overview
This project provides a functional implementation of the Data Encryption Standard (DES) symmetric-key block cipher in Java. Beyond standard encryption and decryption, the application features a custom-built cryptanalysis engine designed to evaluate the security strength of 56-bit block ciphers through exhaustive brute-force search attacks.

Architecture
The suite is structured into two primary components to ensure modularity and clean code design:  The Lab4 Class: Acts as the primary controller, providing a command-line interface (CLI) that allows users to interact with encryption, decryption, and attack modes through a structured menu.  The DES Class: Functions as the cryptographic processing engine, encapsulating the underlying Feistel network logic, key management, and brute-force simulation algorithms. 
Core Cryptographic CapabilitiesSymmetric Encryption: 

The implementation uses the Java Cryptography Architecture (JCA) to perform 64-bit block encryption and decryption using the SecretKeySpec class, mapping standard 56-bit effective keys.  Custom Brute-Force Engine: The application features a custom flexibleBruteForce method that programmatically generates keys and exhaustively searches the key space to recover target plaintext.  Autonomous Plaintext Recognition: To eliminate manual inspection of results, the algorithm dynamically converts decrypted output into a string and utilizes automated matching functions to detect known keywords, halting the attack upon successful recovery.  Scalable Analysis: By utilizing bitwise shifting, the engine efficiently maps loop counters into valid 8-byte DES key arrays, enabling performance testing across variable key lengths. 

Project Evaluation and Insights
This project demonstrates the exponential computational cost associated with breaking symmetric ciphers. The brute-force simulation successfully recovered plaintext after processing billions of key combinations, highlighting the brute-force attack's fundamental reliance on raw processing power. Experimental data indicates that sequential execution on standard CPU hardware is insufficient for cracking the full 56-bit DES key space within a practical timeframe. The research identifies that effective cryptanalysis requires parallelization via multithreading, GPU acceleration, or distributed cloud computing clusters.

Technologies Used

Language: Java (JDK)

Cryptographic Architecture: Java Cryptography Architecture (JCA)

Core Libraries: javax.crypto.Cipher, javax.crypto.SecretKeySpec, java.util.Base64
