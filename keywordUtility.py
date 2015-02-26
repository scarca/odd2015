import json

class keywordUtility:
    '''a VERY thin wrapper around JSON's stored in files.

    Provides an interface to add, remove, modify, etc keywords from a json'''
    def __init__(self, **kwargs):
        '''you MUST provide a key "filename=something" in kwargs.'''
        self.args = kwargs
        if not "filename" in kwargs:
            raise KeyError
        elif "filename" in kwargs:
            self.d = json.load(open(kwargs["filename"], "r"))
        elif "file" in kwargs:
            self.d = json.load(kwargs["file"])
        else:
            raise KeyError
    def addKW(self, kw, val):
        self.d[kw] = val
    def rmKW(self, kw):
        try:
            self.d.pop(kw)
        except KeyError:
            #well, its gone
            pass
    def addVal(self, kw, val):
        self.d[kw].append(val)
    def rmVal(self, kw, val):
        self.d[kw].remove(val)
    def finish(self):
        f = 0
        f = open(self.args["filename"], "w")
        f.write(json.dumps(self.d))
        f.flush()

if __name__ == "__main__":
    print("Please don't use this as a command-line utility, call it from somewhere else instead")
    exit(1)
