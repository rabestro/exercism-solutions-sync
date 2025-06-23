module LogLevels

let message (logLine: string): string = 
    (2 + logLine.IndexOf ":" |> logLine.Substring).Trim()

let logLevel(logLine: string): string = 
    (logLine.[1..(logLine.IndexOf "]" - 1)]).ToLower()  

let reformat(logLine: string): string = 
    $"{message logLine} ({logLevel logLine})"